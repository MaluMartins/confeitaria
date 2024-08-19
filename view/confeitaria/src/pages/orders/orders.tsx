import { useState } from "react";
import { OrderTable } from "../../components/orderTable/orderTable";
import './orders.css';
import { CreateOrderModal } from "../../components/createOrderModal/createOrderModal";

export function Orders() {
    const [isModalOpen, setIsModalOpen] = useState(false);

    const handleOpenModal = () => {
        setIsModalOpen(prev => !prev)
    }
    return (
        <div id="orders-container">  
            <h1>Encomendas</h1>
            <OrderTable />

            {isModalOpen && <CreateOrderModal closeModal={handleOpenModal} order={null}/>}
            <button className="btn-cad-prod" onClick={handleOpenModal}>Cadastrar encomenda</button>

        </div>
    )
}