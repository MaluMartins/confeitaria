import axios, { AxiosPromise } from "axios"
import { useMutation, useQueryClient } from "@tanstack/react-query"
import { OrderData } from "../interface/OrderData";

const API_URL = "http://localhost:8080";

const postData = async (data: OrderData): AxiosPromise<any> => {
    const token = localStorage.getItem("token"); 
    const response = await axios.post(API_URL + "/encomendas", data, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    });
    return response;
}

const updateData = async (data: OrderData): AxiosPromise<any> => {
    const response = axios.put(API_URL + `/encomendas/${data.id_encomenda}`, data);
    return response;
};

export function useOrderDataMutate() {
    const queryClient = useQueryClient();

    const mutate = useMutation({
        mutationFn: postData,
        retry: 2,
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['order-data'] });
        }
    });

    const update = useMutation({
        mutationFn: updateData,
        retry: 2,
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['order-data'] });
        }
    });

    return { mutate, update };
}