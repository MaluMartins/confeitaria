import './orderTable.css';
import { useState } from 'react';
import { OrderRow } from '../orderRow/orderRow';
import { useOrderData } from '../../hooks/useOrderData';
import { OrderData } from '../../interface/OrderData';
import { CreateModal } from '../createModal/createModal';
import { CreateOrderModal } from '../createOrderModal/createOrderModal';

export function OrderTable() {
    const { data } = useOrderData();
    
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [currentOrder, setCurrentOrder] = useState<OrderData | null>(null);

    const handleOpenModal = (order?: OrderData) => {
        setCurrentOrder(order || null);
        setIsModalOpen(true);
    }

    const handleCloseModal = () => {
        setIsModalOpen(false);
        setCurrentOrder(null);
    }

    return (
        <div className="order-table-container">
            <table className="order-table">
                <thead>
                    <tr>
                        <th>Encomenda</th>
                        <th>Produto</th>
                        <th>Cliente</th>
                        <th>Quantidade</th>
                        <th>Data de Entrega</th>
                        <th>Mais informações</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                {data?.map(order => (
                        <OrderRow
                            key={order.id_encomenda}
                            order={order}
                            onEdit={() => handleOpenModal(order)}
                        />
                    ))}
                </tbody>
            </table>
            {isModalOpen && <CreateOrderModal closeModal={handleCloseModal} order={currentOrder} />}
        </div>
    );
}
