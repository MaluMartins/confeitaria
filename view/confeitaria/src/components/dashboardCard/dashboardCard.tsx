import React from 'react';
import './dashboardCard.css';

interface DashboardCardProps {
    icon: JSX.Element;
    title: string;
    value: string;
    iconColor?: string;
}

export function DashboardCard({ icon, title, value, iconColor = '#FF5580'}: DashboardCardProps) {
    const IconWithColor = React.cloneElement(icon, { color: iconColor });

    return (
        <div className="dashboard-card">
            <div className="icon">
                {IconWithColor}
            </div>
            <div className="content">
                <h3>{value}</h3>
                <p>{title}</p>
                
            </div>
        </div>
    );
}
