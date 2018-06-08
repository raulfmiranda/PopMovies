# Popular Movies App

![Alt text](https://raw.githubusercontent.com/raulfmiranda/PopMovies/master/screenshots/all.jpg "PopMovies Screenshots")

### Features:

* A pessoa usuária deve ver uma tela de login e logar para acessar o aplicativo. Se ela não tiver login válido, ela deve ir para uma tela de cadastro e criá-lo.
* O menu principal (BottomBar) da aplicação deve ter: lista de filmes e filmes favoritos.
* A pessoa usuária deve, ao entrar na aplicação, visualizar uma lista dos filmes mais populares da base de dados (https://www.themoviedb.org/documentation/api/).
* Ao clicar para visualizar um filme em particular, ela deve ter algumas informações exibidas como: título, imagem e descrição/sinopse.
* A pessoa usuária, se desejar, pode marcar aquele filme como seu favorito por meio da interface gráfica.
* Quando quiser, a pessoa usuária poderá consultar novamente a lista de filmes ou a lista de seus filmes favoritos.
* A pessoa usuária também pode ver em uma outra tela somente a lista de filmes que ele marcou como favoritos.

### Foi utilizado:

```
* Model–view–presenter (MVP) architectural pattern (por enquanto, apenas no login e cadastro de usuário)
* Firebase para login e persistência de dados.
* SQLite + Room (ORM) para fazer cache local dos dados. 
```