import { useState } from 'react';
import './orderRow.css';
import { ClientModal } from '../clientModal/clientModal';
import { statusMap } from '../../utils/statusMap';
import { OrderData } from '../../interface/OrderData';
import { ClientDetails } from '../clientDetails/clientDetails';
import { useOrderDataDelete } from '../../hooks/useOrderDataDelete';
import { FaTrash, FaPencilAlt } from 'react-icons/fa';

interface OrderRowProps {
    order: OrderData;
    onEdit: () => void;
}

export function OrderRow({ order, onEdit }: OrderRowProps) {
    const [isClientModalOpen, setClientModalOpen] = useState(false);

    const handleClientClick = () => {
        setClientModalOpen(true);
    };

    const closeClientModal = () => {
        setClientModalOpen(false);
    };

    const getStatusClassName = (status: string) => {
        switch (status) {
            case 'NAO_INICIADO':
                return 'status-nao-iniciado';
            case 'EM_ANDAMENTO':
                return 'status-em-andamento';
            case 'CONCLUIDO':
                return 'status-concluido';
            default:
                return '';
        }
    };

    const { mutate: deleteOrder } = useOrderDataDelete();

    const handleDelete = () => {
        deleteOrder(order.id_encomenda!);
    };

    return (
        <tr>
            <td>{order.id_encomenda}</td>
            <td className="produto">{order.produto.nome}</td>
            <td className='cliente' onClick={handleClientClick}>{order.cliente.nome}</td>
            <td>{order.quantidade}</td>
            <td>{order.data_entrega}</td>
            <td className='info-adicional'>{order.info_adicional}</td>
            <td className={getStatusClassName(order.status)}>
                {statusMap[order.status]}
            </td>
            <td className="deletar">
                <FaTrash 
                    onClick={handleDelete} 
                    style={{ cursor: 'pointer', color: '#FF5580' }}
                />
            </td>
            <td className="editar">
                <FaPencilAlt 
                    onClick={onEdit} 
                    style={{ cursor: 'pointer', color: '#FF5580' }}
                />
            </td>

            <ClientModal isOpen={isClientModalOpen} onClose={closeClientModal}>
                <ClientDetails client={order.cliente} />
            </ClientModal>
        </tr>
    );
}
