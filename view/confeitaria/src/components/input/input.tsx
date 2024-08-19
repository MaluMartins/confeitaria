interface InputProps {
    label: string;
    value: string | number;
    updateValue(value: any): void;
    type?: string;
}

export const Input = ({ label, value, updateValue, type = 'text' }: InputProps) => {
    return (
        <div className="input-group">
            <label>{label}</label>
            <input type={type} value={value} onChange={event => updateValue(event.target.value)} />
        </div>
    );
};