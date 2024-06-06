const btn = document.querySelector("#submit-btn");

btn.addEventListener("click", function(e) {
    e.preventDefault();

    // Pegando valores de todos os inputs
    const nome = document.querySelector("#nome").value;
    const telefone = document.querySelector("#telefone").value;
    const endereco = document.querySelector("#endereco").value;
    const bolos = document.querySelector("#bolos").value;
    const quantidade = document.querySelector("#quantidade").value;
    const tamanho = document.querySelector("#tamanho").value;
    const saborMassa = document.querySelector("#sabor-massa").value;
    const saborRecheio = document.querySelector("#sabor-recheio").value;
    const info = document.querySelector("#info").value;

    console.log("Nome:", nome);
    console.log("Telefone:", telefone);
    console.log("Endereço:", endereco);
    console.log("Tipo de Bolo:", bolos);
    console.log("Quantidade:", quantidade);
    console.log("Tamanho:", tamanho);
    console.log("Sabor da Massa:", saborMassa);
    console.log("Sabor do Recheio:", saborRecheio);
    console.log("Informações Adicionais:", info);

    window.location.href = "https://wa.me/5516992471396?text=Meu%20nome%20é%20"+ nome +",%20contato%20" + telefone +".%20%20Endereço:%20" + endereco + ".%20%20Tipo%20de%20bolo:%20" + bolos + ".";
})