import axios, { AxiosPromise } from "axios"
import { useQuery } from "@tanstack/react-query"
import { ClientData } from "../interface/ClientData";

const API_URL = "http://localhost:8080";

const fetchData = async (): AxiosPromise<ClientData[]> => {
    const response = axios.get(API_URL + "/clientes");
    return response;
}

export function useClientData() {
    const query = useQuery({
        queryFn: fetchData,
        queryKey: ['client-data'],
        retry: 2
    })

    return {
        ...query,
        data: query.data?.data,
        refetch: query.refetch
    }
}