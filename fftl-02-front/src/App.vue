<template>
  <Header />
  <body>
    <div id="app">
      
      <div id="container"> <!-- class 추가 -->
        <router-view />
      </div>

    </div>
    <Footer />
  </body>
</template>

<script>
import Header from './components/common/Header';
import Footer from './components/common/Footer';


export default {
  name: 'App',
  components: {
    Header
    ,Footer  
  }
}
</script>

<style>
html,body{
    padding:0; 
    margin: 0 auto;
  }
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  position:static;
}
h1{
  color:#43b984;
  }
table{
  width:100%; 
  border-collapse:collapse;
  }
.wrap{
  width:100%;
  position:relative;
}
#container{
  width:800px; 
  margin:0px auto; 
  padding: 20px; 
  height:600px;
  }
a{
  text-decoration:none;
  }
.btn{
  padding:10px; 
  background:#34445c; 
  color:#fff;
  }
</style>