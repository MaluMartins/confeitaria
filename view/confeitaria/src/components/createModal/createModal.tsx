import { useEffect, useState } from 'react';
import { useProductDataMutate } from '../../hooks/useProductDataMutate';
import { ProductData } from '../../interface/ProductData';
import "./modal.css";

interface InputProps {
    label: string,
    value: string | number,
    updateValue(value: any): void
}

interface ModalProps {
    closeModal(): void
}

const Input = ({ label, value, updateValue}: InputProps) => {
    return (
        <>
            <label>{label}</label>
            <input value={value} onChange={event => updateValue(event.target.value)}/>
        </>
    )
}

export function CreateModal({closeModal}: ModalProps) {
    const [nome, setNome] = useState("");
    const [descricao, setDescricao] = useState("");
    const [valor, setValor] = useState(0);
    const [imagem, setImagem] = useState("");
    const {mutate, isSuccess} = useProductDataMutate();

    const submit = () => {
        const productData: ProductData = {
            nome,
            descricao,
            valor,
            imagem
        }

        mutate(productData)
    }

    useEffect(() => {
        if(!isSuccess) return 
        closeModal();
    }, [isSuccess])

    return(
        <div className="modal-overlay">
            <div className="modal-body">
                <button className="close-btn" onClick={closeModal}>×</button>
                <h2>Cadastre um novo item no cardápio</h2>
                <form className="input-container">
                    <Input label="Nome" value={nome} updateValue={setNome} />
                    <Input label="Descrição" value={descricao} updateValue={setDescricao} />
                    <Input label="Valor" value={valor} updateValue={setValor} />
                    <Input label="Imagem" value={imagem} updateValue={setImagem} />
                    <button onClick={submit} className='btn-secondary'>Postar</button>
                </form>
            </div>
        </div>
    )
}