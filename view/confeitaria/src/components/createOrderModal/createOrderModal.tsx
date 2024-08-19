import { useEffect, useState } from 'react';
import { ProductData } from '../../interface/ProductData';
import { OrderData } from '../../interface/OrderData';
import { ClientData } from '../../interface/ClientData';
import { useOrderDataMutate } from '../../hooks/useOrderDataMutate';
import "./orderModal.css"
import { useProductData } from '../../hooks/useProductData';
import { useClientData } from '../../hooks/useClientData';
import { useOrderData } from '../../hooks/useOrderData';
import { useProductOptions } from '../../hooks/useProductOptions';
import { useClientOptions } from '../../hooks/useClientOptions';

interface ModalProps {
    closeModal(): void,
    order: OrderData | null;
}

interface SelectProps {
    label: string;
    value: string | number;
    updateValue(value: any): void;
    options: Array<{ id: number | string, nome: string }>;
}

const Select = ({ label, value, updateValue, options }: SelectProps) => {
    return (
        <>
            <label>{label}</label>
            <select value={value} onChange={event => updateValue(Number(event.target.value))}>
                {options.map(option => (
                    <option key={option.id} value={option.id}>
                        {option.nome}
                    </option>
                ))}
            </select>
        </>
    );
}

export function CreateOrderModal({ closeModal, order }: ModalProps) {
    const { data: clients } = useClientData();
    const { data: products } = useProductData();

    const [id_encomenda, setIdEncomenda] = useState(order?.id_encomenda || 0);
    const [id_produto, setIdProduto] = useState(order?.id_produto || 0);
    const [id_cliente, setIdCliente] = useState(order?.id_cliente || 0);
    const [quantidade, setQuantidade] = useState(order?.quantidade || 0);
    const [data_entrega, setDataEntrega] = useState(order?.data_entrega || "");
    const [info_adicional, setInfoAdicional] = useState(order?.info_adicional || "");
    const [status, setStatus] = useState<OrderData['status']>(order?.status || 'NAO_INICIADO');

    const { mutate, update } = useOrderDataMutate();
    const { data: productOptions } = useProductOptions();
    const { data: clientOptions } = useClientOptions();

    const submit = () => {
        const orderData: OrderData = {
            id_encomenda,
            id_produto: Number(id_produto),
            id_cliente: Number(id_cliente),
            quantidade,
            data_entrega,
            info_adicional,
            status,
        };

        if (order?.id_encomenda) {
            update.mutate({ ...orderData, id_encomenda: order.id_encomenda });
        } else {
            mutate.mutate(orderData);
            if (!id_produto || !id_cliente) {
                alert('Produto e Cliente são obrigatórios');
                return;
            }
        }
    };

    useEffect(() => {
        if (mutate.isSuccess || update.isSuccess) {
            closeModal();
        }
    }, [mutate.isSuccess, update.isSuccess]);

    return (
        <div className="modal-overlay">
            <div className="modal-body">
                <button className="close-btn" onClick={closeModal}>×</button>
                <h2>{order ? 'Atualizar Encomenda' : 'Criar Nova Encomenda'}</h2>
                <form className="input-container">
                    {/* <label>ID encomenda</label>
                    <input type="number" value={id_encomenda} onChange={e => setIdEncomenda(Number(e.target.value))}/> */}

                    <label>Produto</label>
                    <select value={id_produto} onChange={e => setIdProduto(Number(e.target.value))}>
                        <option value="">Selecione um produto</option>
                        {products?.map(product => (
                            <option key={product.id_produto} value={product.id_produto}>
                                {product.nome}
                            </option>
                        ))}
                    </select>

                    <label>Cliente</label>
                    <select onChange={e => setIdCliente(Number(e.target.value))}>
                        <option value="">Selecione um cliente</option>
                        {clients?.map(client => (
                            <option key={client.id_cliente} value={client.id_cliente}>
                                {client.nome}
                            </option>
                        ))}
                    </select>

                    {/* {productOptions && (
                        <Select label="Produto" value={id_produto} updateValue={setIdProduto} options={productOptions} />
                    )}
                    
                    {clientOptions && (
                        <Select label="Cliente" value={id_cliente} updateValue={setIdCliente} options={clientOptions} />
                    )} */}

                    <label>Quantidade</label>
                    <input type="number" value={quantidade} onChange={e => setQuantidade(Number(e.target.value))} />

                    <label>Data de Entrega</label>
                    <input type="date" value={data_entrega} onChange={e => setDataEntrega(e.target.value)} />

                    <label>Status</label>
                    <select value={status} onChange={e => setStatus(e.target.value as 'NAO_INICIADO' | 'EM_ANDAMENTO' | 'CONCLUIDO')}>
                        <option value="NAO_INICIADO">Não iniciado</option>
                        <option value="EM_ANDAMENTO">Em andamento</option>
                        <option value="CONCLUIDO">Concluído</option>
                    </select>

                    <label>Info Adicional</label>
                    <textarea value={info_adicional} onChange={e => setInfoAdicional(e.target.value)} />

                    <button type="button" onClick={submit} className='btn-secondary'>{order ? 'Atualizar' : 'Criar'}</button>
                </form>
            </div>
        </div>
    )
}