<template>
    <div>
        <form>
            <p><span>아이디 :</span><input type="text" v-model="username" ref="username"></p>
            <p><span>비밀번호 :</span><input type="password" v-model="password" ref="password"></p>
        </form>
    </div>
    <div class="btnWrap">
        <a href="javascript:;" @click="golist" class="btn">목록</a>
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
      
    },
    methods: {
        golist(){
            this.$router.push({ path: "/" });
        }
        //로그인하여 토큰 정보를 store 에 담는다.
        //uid 와 nickname도 사용하기 쉽게 담아보자.
        ,login(){ 
            this.form = {
                username: this.username,
                password: this.password
            }
            this.$http.post("http://localhost:3000/user/login/",this.form)
            .then((res) => {
                if(res.status == 201){
                  this.$store.commit('setToken', res.data.access_token);
                  this.myInfo();
                  this.golist();
                }
                console.log(this.$store.state.token);
            })
            .catch((err) => {
              alert(err);
            })
        }
        ,myInfo(){
          this.$http.get("http://localhost:3000/user/profile/", { headers:{'Authorization' : 'Bearer ' + this.$store.state.token }} )
          .then((res) => {
            console.log(res);
            if(res.status == 200){
              this.$store.commit('setNickname', res.data.nickname);
              this.$store.commit('setUid', res.data.uid);
            }
          })
          .catch((err) =>{
            alert(err);
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