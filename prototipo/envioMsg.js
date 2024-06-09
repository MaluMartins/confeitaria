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

    urltext = "Meu nome é "+ nome +", contato " + telefone +".  Endereço: " + endereco + ". Tipo de bolo: " + bolos + ", quantidade: " + quantidade + ", tamanho: " + tamanho + ", sabor da massa: " + saborMassa + ", sabor do recheio: " + saborRecheio + ". Obs: "

    const numero = require("./numero.js")

    window.location.href = "https://wa.me/"+numero+"?text=" + urltext;
})