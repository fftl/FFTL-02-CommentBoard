<template>
  <div>
    <h1>회원 가입</h1>
    <div class="AddWrap">
      <form class="join" @submit="checkForm">
        <p>
          <span>아이디 : </span
          ><input type="text" v-model="username" ref="username" />
        </p>
        <p>
          <span>비밀번호 : </span
          ><input type="text" v-model="password" ref="password" />
        </p>
        <p>
          <span>비밀번호 확인 : </span
          ><input type="text" v-model="password_check" ref="password_check" />
        </p>
        <p>
          <span>닉네임 : </span
          ><input type="text" v-model="nickname" ref="nickname" />
        </p>
      </form>
    </div>
    <div class="btnWrap">
      <a href="javascript:;" @click="back" class="btn">목록</a>
      <a href="javascript:;" @click="saveUser" class="btnAdd btn">가입완료</a>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    //변수 생성
    return {
      username: "",
      password: "",
      password_check: "",
      nickname: "",
      form: "",
    };
  },
  mounted() {},
  methods: {
    checkForm(e) {
      
      if(this.username == "" || this.username == null){
        this.errors.push("아이디를 입력해 주세요.");
      }
      e.preventDefault();

    }
    ,goHome() {
      //리스트 화면으로 이동 함수
      this.$router.push({ path: "/", query: this.body });
    }
    ,saveUser() {
      this.form = {
        username: this.username,
        password: this.password,
        nickname: this.nickname,
      };

      this.$http
        .post("http://localhost:3000/user/saveUser", this.form)
        .then((res) => {
          console.log(res);
          alert("회원가입에 성공했습니다.");
          this.goHome();
        })
        .catch((err) => {
          alert(err);
          console.log(err);
        });
    },
  },
};
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