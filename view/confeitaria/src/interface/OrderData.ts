export interface OrderData {
    id_encomenda?: number;
    id_produto: number;
    id_cliente: number;
    quantidade: number;
    data_entrega: string;
    info_adicional: string;
    status: 'NAO_INICIADO' | 'EM_ANDAMENTO' | 'CONCLUIDO';
}