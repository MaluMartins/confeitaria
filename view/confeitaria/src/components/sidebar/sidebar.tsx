import { useState } from 'react';
import { useAuth } from '../../context/AuthContext';
import { Link } from 'react-router-dom';
import './sidebar.css';

export function Sidebar() {
    const { logout } = useAuth();
    const [isSidebarOpen, setIsSidebarOpen] = useState(false);

    const toggleSidebar = () => {
        setIsSidebarOpen(!isSidebarOpen);
    };

    return (
        <>
            <button className={`hamburger ${isSidebarOpen ? 'open' : ''}`} onClick={toggleSidebar}>
                {isSidebarOpen ? '✕' : '☰'}
            </button>
            <div className={`sidebar ${isSidebarOpen ? 'open' : ''}`}>
                <h2>Menu</h2>
                <nav>
                    <ul>
                        <li>
                            <Link to="/home">Home</Link>
                        </li>
                        <li>
                            <Link to="/products">Meus produtos</Link>
                        </li>
                        <li>
                            <Link to="/orders">Minhas encomendas</Link>
                        </li>
                        <li>
                            <Link to="/estoque">Estoque</Link>
                        </li>
                        <li onClick={logout}>
                            <Link to="/">Sair</Link>
                        </li>
                    </ul>
                </nav>
            </div>
        </>
    );
}
