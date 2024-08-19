import { ClientData } from '../../interface/ClientData';

interface ClientDetailsProps {
    client: ClientData;
}

export function ClientDetails({ client }: ClientDetailsProps) {
    return (
        <div>
            <h2>Dados do Cliente</h2>
            <p><strong>Nome:</strong> {client.nome}</p>
            <p><strong>Email:</strong> {client.email}</p>
            <p><strong>Telefone:</strong> {client.telefone}</p>
            <p><strong>Cidade:</strong> {client.cidade}</p>
            <p><strong>Bairro:</strong> {client.bairro}</p>
            <p><strong>Logradouro:</strong> {client.logradouro}</p>
            <p><strong>Número da Casa:</strong> {client.numero_casa}</p>
        </div>
    );
}
