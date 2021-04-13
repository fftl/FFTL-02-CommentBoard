<template>
  <div>
    <h1>게시판 등록</h1>

    <div class="AddWrap">
      <form>
        <table class="tbAdd">
          <colgroup>
            <col width="15%" />
            <col width="*" />
          </colgroup>
          <tr>
            <th>제목</th>
            <td><input type="text" v-model="title" ref="title" /></td>
          </tr>
          <tr>
            <th>작성자</th>
            <td><input type="text" v-model="nickname" ref="nickname" readonly="readonly"/></td>
          </tr>
          <tr>
            <th>내용</th>
            <td><textarea v-model="content" ref="content"></textarea></td>
          </tr>
        </table>
      </form>
    </div>

    <div class="btnWrap">
      <a href="javascript:;" @click="goList" class="btn">목록</a>
      <a
        href="javascript:;"
        @click="addBoard"
        class="btnAdd btn"
        >등록</a
      >
    </div>
  </div>
</template>

<script>
let today = new Date();
let year = today.getFullYear(); // 년도
let month = today.getMonth() + 1; // 월
let date = today.getDate(); // 날짜

let regdate = year + "-" + month + "-" + date;

export default {
  data() {
    return {
      title: "",
      nickname: "",
      content: "",
      form: "",
    };
  },
  mounted() {
    this.nickname = this.$store.state.nickname;
    console.log(this.$store.state.nickname);
    console.log(this.$store.state.uid);
    console.log(this.$store.state.token);
  },
  methods: {
    goList() {
      //리스트 화면으로 이동 함수
      this.$router.push({ path: "/board/list" });
    },
    addBoard() {
      if (!this.title) {
        alert("제목을 입력해 주세요");
        this.$refs.title.focus(); //방식으로 선택자를 찾는다.
        return;
      }
      if (!this.content) {
        alert("내용을 입력해 주세요");
        this.$refs.content.focus(); //방식으로 선택자를 찾는다.
        return;
      }

      this.form = {
        //backend로 전송될 POST 데이터
        title: this.title,
        nickname: this.nickname,
        content: this.content,
        bregdate: regdate,
        uid: this.$store.state.uid
      };
      console.log(this.form);
      this.$http
        .post("http://localhost:3000/board/", this.form, { headers : {'Authorization': 'Bearer ' + this.$store.state.token}})
        .then((res) => {
          console.log(res);
          if (res.status == 201) {
            alert("등록되었습니다.");
            this.goList();
          } else {
            alert("실행 중 실패했습니다.\n다시 이용해 주세요");
          }
        })
        .catch((err) => {
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