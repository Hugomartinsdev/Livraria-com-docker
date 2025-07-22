
# Livraria
CRUD de uma livraria com banco de dado sqlite e docker
Foi utilizado somente Java, sem extensão ou ferramentas
## Requisitos
<ol>
    <li>Java igual ou superior ao 21</li>
    <li>Ter docker notebook instalado</li>
    <li>Ter o jdbc,no caso desse código foi usado a versão 3.50.2.0,</li>
    <li>Ter o dockerfile,disponibilizado</li>
</ol>

## Função
1-Cadastrar um novo livro<br>
2-Listar todos os livros cadastrados<br>
3-Procurar um livro por ID<br>
4-Editar um livro<br>
5-Apagar um Livro<br>
6-Comprar um livro<br>
7-Mostrar o log de compras

## Como rodar
<ul>
  <li>
    No terminal ou CMD use o comando para construir a imagem:
docker build -t livraria .
  </li>
  <li>Após a construção, para iniciar o programa utilize o comando:
docker run -it --rm livraria</li>
</ul>

