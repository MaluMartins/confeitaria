import { DashboardCard } from "../../components/dashboardCard/dashboardCard";
import { FaShoppingCart, FaDollarSign, FaUsers } from 'react-icons/fa';
import "./home.css"

export function Home() {
    return (
        <div id="home-container">
            <h1>Bem vindo!</h1>
            <div className="dashboard-cards">
            <DashboardCard 
                icon={<FaShoppingCart />}
                title="Vendas Totais"
                value="500"
                iconColor="#FF5580"
            />
            <DashboardCard 
                icon={<FaDollarSign />}
                title="Lucro Total"
                value="R$5.000"
                iconColor="#FF5580"
            />
            <DashboardCard 
                icon={<FaUsers />}
                title="Número de Clientes"
                value="350"
                iconColor="#FF5580"
            />
            </div>
            <h2>Gerencie suas vendas e estoque de produtos!</h2>
        </div>
    )
}