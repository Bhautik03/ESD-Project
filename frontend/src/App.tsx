import React, { useState, useEffect } from 'react';
import './App.css';
import Dashboard from './pages/Dashboard';
import Login from './pages/Login';

function App(): JSX.Element {
  const [isAuthenticated, setIsAuthenticated] = useState<boolean | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    // Check for error parameters in URL (from OAuth callback)
    const urlParams = new URLSearchParams(window.location.search);
    const errorParam = urlParams.get('error');
    
    if (errorParam) {
      // Clear the error from URL
      window.history.replaceState({}, document.title, window.location.pathname);
      
      // Set appropriate error message
      switch (errorParam) {
        case 'cancelled':
        case 'access_denied':
          setError('Login was cancelled. Please try again.');
          break;
        case 'no_code':
          setError('Authorization failed. Please try again.');
          break;
        case 'invalid_token':
          setError('Token validation failed. Please try again.');
          break;
        case 'unauthorized':
          setError('You are not authorized to access this application.');
          break;
        case 'auth_failed':
          setError('Authentication failed. Please try again.');
          break;
        default:
          setError('An error occurred during authentication. Please try again.');
      }
      setIsAuthenticated(false);
      setLoading(false);
      return;
    }
    
    // Check if authenticated flag is set (successful login)
    if (urlParams.get('authenticated') === 'true') {
      window.history.replaceState({}, document.title, window.location.pathname);
      // Add a delay to ensure cookies are set before checking auth status
      setTimeout(() => {
        checkAuthStatus();
      }, 300);
      return;
    }
    
    checkAuthStatus();
  }, []);

  const checkAuthStatus = async (retryCount = 0) => {
    try {
      const response = await fetch('http://localhost:8080/api/auth/status', {
        credentials: 'include', // Important: send cookies
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
      });
      
      if (!response.ok) {
        throw new Error('Auth check failed');
      }
      
      const isAuth = await response.json();
      setIsAuthenticated(isAuth);
      
      // If not authenticated and we haven't retried, try once more after a short delay
      if (!isAuth && retryCount === 0) {
        setTimeout(() => {
          checkAuthStatus(1);
        }, 500);
        return;
      }
      
      // Only set loading to false if we're done (either authenticated or retried)
      if (isAuth || retryCount > 0) {
        setLoading(false);
      }
    } catch (error) {
      console.error('Auth check failed:', error);
      // Retry once if first attempt fails
      if (retryCount === 0) {
        setTimeout(() => {
          checkAuthStatus(1);
        }, 500);
        return;
      }
      setIsAuthenticated(false);
      setLoading(false);
    }
  };

  const handleLogout = () => {
    window.location.href = 'http://localhost:8080/signout';
  };

  const handleRetryLogin = () => {
    setError(null);
    setLoading(true);
    setIsAuthenticated(null);
    checkAuthStatus();
  };

  if (loading) {
    return (
      <div className="App" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
        <div>Loading...</div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="login-container">
        <div className="login-card">
          <div className="login-header">
            <div className="login-icon">⚠️</div>
            <h1>Authentication Error</h1>
            <p>{error}</p>
          </div>
          <div className="login-content">
            <button 
              onClick={handleRetryLogin}
              className="google-login-btn"
            >
              <span>Try Again</span>
            </button>
          </div>
        </div>
      </div>
    );
  }

  if (!isAuthenticated) {
    return <Login />;
  }

  return (
    <div className="App">
      <Dashboard onLogout={handleLogout} />
    </div>
  );
}

export default App;
