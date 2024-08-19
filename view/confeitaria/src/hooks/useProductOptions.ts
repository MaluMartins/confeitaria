import axios, { AxiosPromise } from "axios";
import { useQuery } from "@tanstack/react-query";

const API_URL = "http://localhost:8080";

interface ProductOption {
    id: number;
    nome: string;
}

const fetchProductOptions = async (): AxiosPromise<ProductOption[]> => {
    const response = axios.get(API_URL + "/produtos");
    return response;
}

const mapProductFromApi = (data: any): ProductOption => {
    return {
        id: data.id_produto,
        nome: data.nome
    };
}

export function useProductOptions() {
    const query = useQuery({
        queryFn: fetchProductOptions,
        queryKey: ['product-options'],
        retry: 2
    })

    return {
        ...query,
        data: query.data?.data.map(mapProductFromApi),
        refetch: query.refetch
    }
}
