<template>
  <div>
    <h2>게시판 리스트</h2>
    <button class="goWrite" @click="goWrite">글 작성</button>
    <PaginatedList :listArray="pageArray" />
  </div>
</template>

<script>
import PaginatedList from "./PaginatedList";
export default {
  components: {
    PaginatedList,
  },
  data() {
    return {
      pageArray: [],
      header: [],
    };
  },
  mounted() {
    this.loginCheck();
    this.getList();
  },
  methods: {
    loginCheck() {
      if (this.$store.state.token == "") {
        alert("잘못된 접근 입니다.");
        this.$router.push({ path: "/" });
      }
    },
    getList() {
      this.$http
        .get("https://fftl-02-back.herokuapp.com/board/", {
          headers: { Authorization: this.$store.state.token },
        })
        .then((res) => {
          if (res.data.success) {
            this.pageArray = res.data.data;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    goWrite() {
      this.$router.push({ path: "./write" }); //추가한 상세페이지 라우터
    },
    myInfo() {
      this.$http
        .get("https://fftl-02-back.herokuapp.com/user/profile", {
          headers: { Authorization: this.$store.state.token },
        })
        .then((res) => {
          if (res.status == 200) {
            this.$store.commit("setNickname", res.data.nickname);
            this.$store.commit("setUser_id", res.data.user_id);
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
.goWrite {
  float: right;
  width: 5rem;
  height: 2rem;
  margin-bottom: 10px;
}
</style>