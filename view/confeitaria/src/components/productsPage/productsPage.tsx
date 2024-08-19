import { useProductData } from "../../hooks/useProductData";
import { Card } from "../card/card";
import { CreateModal } from "../createModal/createModal";
import "./products.css";
import { useState } from 'react';
import axios from 'axios';

const API_URL = 'http://localhost:8080';

export function Products() {
    const { data, refetch  } = useProductData();
    const [products, setProducts] = useState<any[]>([]);
    
    const [isModalOpen, setIsModalOpen] = useState(false);

    const handleDelete = async (id: number | undefined) => {
        try {
          await axios.delete(`${API_URL}/produtos/${id}`);
          setProducts(products.filter(product => product.id !== id));
          refetch();
        } catch (error) {
          console.error('Erro ao deletar produto:', error);
        }
      };

    const handleOpenModal = () => {
        setIsModalOpen(prev => !prev)
    }

    return (
        <div className="container">
            <h1>Produtos</h1>
            <div className="card-grid">
                {data?.map(productData => <Card
                    key={productData.id_produto}
                    id={productData.id_produto}
                    titulo={productData.nome}
                    descricao={productData.descricao}
                    preco={productData.valor}
                    imagem={productData.imagem}
                    handleDelete={handleDelete}
                />)}
            </div>

            {isModalOpen && <CreateModal closeModal={handleOpenModal} />}
            <button className="btn-cad-prod" onClick={handleOpenModal}>Cadastrar produto</button>

        </div>
    )
}