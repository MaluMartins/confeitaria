import "./card.css"

interface CardProps {
    id: number | undefined,
    titulo: string,
    descricao: string,
    preco: number,
    imagem: string,
    handleDelete: (id: number | undefined) => void,
}

export function Card({ id, titulo, descricao, preco, imagem, handleDelete }: CardProps) {

    return (
        <>
            <div className="card" key={id}>
                <img src={imagem} />
                <h2>{titulo}</h2>
                <p className="descricao">{descricao}</p>
                <p className="preco">R${preco}</p>
                <span className="delete" onClick={() => handleDelete(id)}>Deletar</span>
            </div>
        </>
    )
}