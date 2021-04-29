# stoom

## Teste de Qualificação Backend STOOM
O teste consiste em criar no padrão REST, um CRUD (Create, Read, Update, Delete) de uma entidade endereço com os seguintes atributos:

<ol>
<li>id*</li>
<li>streetName*</li>
<li>number*</li>
<li>complement</li>
<li>neighbourhood*</li>
<li>city*</li>
<li>state*</li>
<li>country*</li>
<li>zipcode*</li>
<li>latitude</li>
<li>longitude</li>
</ol>
Obs.: Os atributos marcados com * devem ser obrigatórios

### Obrigatório
<ol>
<li>Deve-se utilizar Java para criação desse CRUD. O framework pode ser o que se sentir mais à vontade.</li>

<li>Deve-se criar um repositório público no github para compartilhar o teste e este ser enviado ao examinador na conclusão</li>
</ol>

### Diferenciais
<ol>
<li>Quando latitude e longitude não forem informados, o sistema precisa buscar essa informação utilizando a Geocoding API do Google (https://developers.google.com/maps/documentation/geocoding/start)</li>
<li>Criar um Dockerfile funcional com o projeto</li>
Obs.: Não precisa se preocupar com banco de dados no Dockerfile, ele será executado usando em nosso ambiente que já irá considerar isso
<li>Criar ao menos um teste unitário básico para cada ação (Create, Read, Update, Delete)</li>
</ol>
