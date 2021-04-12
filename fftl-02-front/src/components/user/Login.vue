<template>
    <div>
        <form>
            <p><span>아이디 :</span><input type="text" v-model="username" ref="username"></p>
            <p><span>비밀번호 :</span><input type="text" v-model="password" ref="password"></p>
        </form>
    </div>
    <div class="btnWrap">
        <a href="javascript:;" @click="back" class="btn">목록</a>
        <a href="javascript:;" @click="login" class="btnAdd btn">로그인</a>
    </div>
</template>
<script>
export default {
    data() {
        return{
            form:"",
            username:"",
            password:"",
        }
    },
    mounted(){
      console.log(this.$store.state.token);
    },
    methods: {
        back(){
            this.$router.push({ path: "./list" });
        },
        login(){
            this.form = {
                username: this.username,
                password: this.password
            }
            this.$http.post("http://localhost:3000/user/login",this.form)
            .then((res) => {
                console.log(res);
                if(res.status == 201){
                  //
                  this.$store.commit('setToken', res.data.access_token);
                }
                console.log(this.$store.state.token);
            })
            .catch((err) => {
                console.log(err);
            })
        }
        
    }
}
</script>
<style scoped>
.tbAdd {
  border-top: 1px solid #888;
}
.tbAdd th,
.tbAdd td {
  border-bottom: 1px solid #eee;
  padding: 5px 0;
}
.tbAdd td {
  padding: 10px 10px;
  box-sizing: border-box;
}
.tbAdd td input {
  width: 100%;
  min-height: 30px;
  box-sizing: border-box;
  padding: 0 10px;
}
.tbAdd td textarea {
  width: 100%;
  min-height: 300px;
  padding: 10px;
  box-sizing: border-box;
}
.btnWrap {
  text-align: center;
  margin: 20px 0 0 0;
}
.btnWrap a {
  margin: 0 10px;
}
.btnAdd {
  background: #43b984;
}
.btnDelete {
  background: #f00;
}
</style>