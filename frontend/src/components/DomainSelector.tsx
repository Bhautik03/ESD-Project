import React from 'react';
import './DomainSelector.css';
import { DomainSummary } from '../types';

interface DomainSelectorProps {
  domains: DomainSummary[];
  selectedDomain: number | null;
  onSelect: (domainId: number) => void;
  loading: boolean;
}

const DomainSelector: React.FC<DomainSelectorProps> = ({
  domains,
  selectedDomain,
  onSelect,
  loading,
}) => {
  return (
    <div className="domain-selector">
      <h2>Select Domain</h2>
      {loading && domains.length === 0 ? (
        <div className="loading-text">Loading domains...</div>
      ) : (
        <div className="domain-list">
          {domains.length === 0 ? (
            <div className="no-domains">No domains available</div>
          ) : (
            domains.map((domain) => (
              <button
                key={domain.id}
                className={`domain-button ${
                  selectedDomain === domain.id ? 'active' : ''
                }`}
                onClick={() => onSelect(domain.id)}
                disabled={loading}
              >
                <span className="domain-icon">🎓</span>
                <span className="domain-name">{domain.program}</span>
              </button>
            ))
          )}
        </div>
      )}
    </div>
  );
};

export default DomainSelector;

