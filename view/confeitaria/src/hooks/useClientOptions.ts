import axios, { AxiosPromise } from "axios";
import { useQuery } from "@tanstack/react-query";

const API_URL = "http://localhost:8080";

interface ClientOption {
    id: number;
    nome: string;
}

const fetchClientOptions = async (): AxiosPromise<ClientOption[]> => {
    const response = axios.get(API_URL + "/clientes");
    return response;
}

const mapClientFromApi = (data: any): ClientOption => {
    return {
        id: data.id_cliente,
        nome: data.nome
    };
}

export function useClientOptions() {
    const query = useQuery({
        queryFn: fetchClientOptions,
        queryKey: ['client-options'],
        retry: 2
    })

    return {
        ...query,
        data: query.data?.data.map(mapClientFromApi),
        refetch: query.refetch
    }
}
