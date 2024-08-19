import axios, { AxiosPromise } from "axios"
import { OrderData } from "../interface/OrderData"
import { useQuery } from "@tanstack/react-query"

const API_URL = "http://localhost:8080";

const fetchData = async (): AxiosPromise<OrderData[]> => {
    const response = axios.get(API_URL + "/encomendas");
    return response;
}

export function useOrderData() {
    const query = useQuery({
        queryFn: fetchData,
        queryKey: ['order-data'],
        retry: 2
    })

    return {
        ...query,
        data: query.data?.data,
        refetch: query.refetch
    }
}