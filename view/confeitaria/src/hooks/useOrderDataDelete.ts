import axios from "axios";
import { useMutation, useQueryClient } from "@tanstack/react-query";

const API_URL = "http://localhost:8080";

const deleteOrder = async (id: number) => {
    await axios.delete(`${API_URL}/encomendas/${id}`);
};

export function useOrderDataDelete() {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: deleteOrder,
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['order-data'] });
        },
    });
}
