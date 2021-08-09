<template>
  <div class="container">
    <h2>
      홈 페이지 입니다.
    </h2>
    <div>
      <span id="login"><a href="/user/login">로그인 </a></span>
      <span id="join"><a href="/user/join">회원가입</a></span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
