<template>
  <div>
    <form>
      <p>
        <span>아이디 :</span
        ><input
          class="username"
          type="text"
          v-model="username"
          ref="username"
        />
      </p>
      <p>
        <span>비밀번호 :</span
        ><input
          class="password"
          type="password"
          v-model="password"
          ref="password"
        />
      </p>
    </form>
  </div>
  <div class="btnWrap">
    <a href="javascript:;" @click="goIndex" class="btn">취소</a>
    <a href="javascript:;" @click="login" class="btnAdd btn">로그인</a>
  </div>
</template>
<script>
export default {
  data() {
    return {
      form: "",
      username: "",
      password: "",
    };
  },
  mounted() {},
  methods: {
    golist() {
      this.$router.push({ path: "/board/list" });
    },
    goIndex() {
      this.$router.push({ path: "/" });
    },
    //로그인하여 토큰 정보를 store 에 담는다.
    //uid 와 nickname도 사용하기 쉽게 담아보자.
    login() {
      if (!this.username) {
        alert("아이디를 입력해 주세요.");
        document.getElementsByClassName("username")[0].focus();
      } else if (!this.password) {
        alert("비밀번호를 입력해 주세요.");
        document.getElementsByClassName("password")[0].focus();
      } else {
        this.form = {
          username: this.username,
          password: this.password,
        };
        console.log(this.form);
        this.$http
          .post(
            "https://fftl-02-springboot.herokuapp.com/user/login",
            this.form
          )
          .then((res) => {
            console.log(res);
            if (
              (res.status == 201 || res.status == 200) &&
              res.data.success != "fail"
            ) {
              this.$store.commit("setToken", res.data.access_token);
              this.$store.commit("setLoginCheck", true);
              this.myInfo();
              this.golist();
            } else {
              // 로그인 정보가 일치하지 않을 때!!
              alert("아이디나 비밀번호가 틀렸습니다. 확인해주세요.");
            }
            // console.log(this.$store.state.token);
          })
          .catch((err) => {
            alert(err);
          });
      }
    },
    myInfo() {
      this.$http
        .get("https://fftl-02-springboot.herokuapp.com/user/profile", {
          headers: { Authorization: "Bearer " + this.$store.state.token },
        })
        .then((res) => {
          console.log(res);
          if (res.status == 200) {
            this.$store.commit("setNickname", res.data.nickname);
            this.$store.commit("setUid", res.data.uid);
          }
        })
        .catch((err) => {
          alert(err);
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