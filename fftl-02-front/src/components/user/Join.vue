<template>
  <div>
    <h1>회원 가입</h1>
    <div class="AddWrap">
      <form class="join" @submit="checkForm">
        <p>
          <span>아이디 : </span
          ><input type="text" v-model="username" ref="username" />&nbsp;&nbsp;<a
            type="button"
            @click="checkId"
            class="checkId"
          >
            아이디 중복확인
          </a>
        </p>
        <p>
          <span>비밀번호 : </span
          ><input type="password" v-model="password" ref="password" />
        </p>
        <p>
          <span>비밀번호 확인 : </span
          ><input type="password" v-model="passwordCheck" ref="passwordCheck" />
          <span
            v-if="
              (password != '' || passwordCheck != '') &&
              password != passwordCheck
            "
            >비밀번호가 다릅니다.</span
          >
          <span
            v-else-if="
              (password != '' || passwordCheck != '') &&
              password == passwordCheck
            "
            >비밀번호가 같습니다.</span
          >
        </p>
        <p>
          <span>닉네임 : </span
          ><input type="text" v-model="nickname" ref="nickname" />&nbsp;&nbsp;<a
            type="button"
            @click="checkNickname"
            class="checkNickname"
          >
            닉네임 중복확인
          </a>
        </p>
      </form>
    </div>
    <div class="btnWrap">
      <a href="javascript:;" @click="goHome" class="btn">목록</a>
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
      passwordCheck: "",
      nickname: "",
      form: "",
      usernameCheck: false,
      nicknameCheck: false,
      formCheck: false,
    };
  },
  mounted() {},
  methods: {
    checkId() {
      if (this.username == "" || this.username == null) {
        alert("아이디를 입력해 주세요.");
      } else {
        this.$http
          .post("http://127.0.0.1:8080/user/checkId?username=" + this.username)
          .then((res) => {
            if (res.data.statusCode == 200) {
              confirm("사용할 수 있는 아이디입니다.");
              this.usernameCheck = true;
            } else {
              alert("중복된 아이디 입니다.");
              this.usernameCheck = false;
            }
          });
      }
    },

    checkNickname() {
      if (this.nickname == "" || this.nickname == null) {
        alert("닉네임을 입력해주세요.");
      } else {
        this.$http
          .post(
            "http://127.0.0.1:8080/user/checkNickname?nickname=" + this.nickname
          )
          .then((res) => {
            if (res.data.statusCode == 200) {
              alert("사용할 수 있는 닉네임입니다.");
              this.nicknameCheck = true;
            } else {
              alert("중복된 닉네임 입니다.");
              this.nicknameCheck = false;
            }
          });
      }
    },

    checkForm() {
      console.log("checkForm()");

      if (this.username == "" || this.username == null) {
        alert("아이디를 입력해 주세요.");
      } else if (!this.usernameCheck) {
        alert("아이디 중복확인을 해주세요.");
      } else if (this.password == "" || this.password == null) {
        alert("비밀번호를 입력해 주세요.");
      } else if (!this.nicknameCheck) {
        alert("닉네임 중복확인을 해주세요.");
      } else {
        this.formCheck = true;
      }
    },

    goHome() {
      //리스트 화면으로 이동 함수
      this.$router.push({ path: "/", query: this.body });
    },

    saveUser() {
      this.checkForm();
      if (this.formCheck) {
        this.form = {
          username: this.username,
          password: this.password,
          nickname: this.nickname,
        };

        this.$http
          .post("http://127.0.0.1:8080/user/saveUser", this.form)
          .then((res) => {
            console.log(res);
            alert("회원가입에 성공했습니다.");
            this.goHome();
          })
          .catch((err) => {
            alert(err);
            console.log(err);
          });
      }
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
.checkId,
.checkNickname {
  font-size: 12px;
  padding: 5px;
  background: #43b984;
  color: #fff;
  cursor: pointer;
}
</style>