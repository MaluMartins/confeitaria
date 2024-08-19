import { Outlet } from "react-router-dom";
import { Sidebar } from "../../components/sidebar/sidebar";
import "./main.css";

export const MainLayout: React.FC = () => {
    return (
        <div className="main-layout">
            <Sidebar />
            <div className="content">
                <Outlet />
            </div>
        </div>
    )
}