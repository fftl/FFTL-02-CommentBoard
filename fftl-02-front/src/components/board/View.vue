<template>
  <div>
    <h1>게시판 상세보기</h1>

    <div class="AddWrap">
      <form>
        <table class="tbAdd">
          <colgroup>
            <col width="15%" />
            <col width="*" />
          </colgroup>
          <tr>
            <th>제목</th>
            <td>{{ title }}</td>
          </tr>
          <tr>
            <th>작성자</th>
            <td>{{ nickname }}</td>
          </tr>
          <tr>
            <th>날짜</th>
            <td>{{ bregdate }}</td>
          </tr>
          <tr>
            <th>내용</th>
            <td class="txt_cont" v-html="content"></td>
          </tr>
        </table>
      </form>
    </div>

    <div v-if="uCheck" class="AddWrap">
      <form>
        <table class="tbAdd">
          <colgroup>
            <col width="15%" />
            <col width="*" />
          </colgroup>
          <tr>
            <th>제목</th>
            <td>{{ title }}</td>
          </tr>
          <tr>
            <th>작성자</th>
            <td>{{ nickname }}</td>
          </tr>
          <tr>
            <th>날짜</th>
            <td>{{ bregdate }}</td>
          </tr>
          <tr>
            <th>내용</th>
            <td class="txt_cont" v-html="content"></td>
          </tr>
        </table>
      </form>
    </div>

    <div v-if="uCheck" class="btnWrap">
      <a href="javascript:;" @click="goList" class="btn">목록</a>
      <a href="javascript:;" @click="fnMod" class="btnAdd btn">수정</a>
      <a href="javascript:;" @click="fnMod" class="btnAdd btn">삭제</a>
    </div>
    <div v-else class="btnWrap">
      <a href="javascript:;" @click="goList" class="btn">목록</a>
    </div>
    <Comments :commentsArray="comments" />
  </div>
</template>

<script>
import Comments from "./Comments";
export default {
  components: {
    Comments,
  },
  data() {
    return {
      uCheck: false,
      bid: this.$route.query.bid,
      uid: "",
      title: "",
      nickname: "",
      bregdate: "",
      content: "",
      comments: [],
    };
  },
  mounted() {
    this.getOneBoard();
    this.getComments();
  },
  methods: {
    userCheck() {
      if (this.$store.state.uid == this.uid) {
        this.uCheck = true;
      }
    },
    getOneBoard() {
      this.$http
        .get("http://localhost:3000/board/" + this.bid, {
          headers: { Authorization: "Bearer " + this.$store.state.token },
        })
        .then((res) => {
          console.log(res);
          this.body = res.data;
          this.title = res.data.title;
          this.nickname = res.data.nickname;
          this.bregdate = res.data.bregdate;
          this.content = res.data.content.replace(/(\n)/g, "<br/>");
          this.uid = res.data.user.uid;
          this.userCheck();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    goList() {
      delete this.body.num;
      this.$router.push({ path: "/board/list", query: this.body });
    },
    getComments() {
      this.$http
        .get("http://127.0.0.1:3000/comment/" + this.bid, {
          headers: { Authorization: "Bearer " + this.$store.state.token },
        })
        .then((res) => {
          console.log(res.data);
          this.comments = res.data;
        })
        .catch((err) => {
          alert(err);
        });
    },
    // ,fnModProc() {
    //   if (!this.title) {
    //     alert("제목을 입력해 주세요");
    //     this.$refs.title.focus(); //방식으로 선택자를 찾는다.
    //     return;
    //   }

    //   this.form = {
    //     title: this.title,
    //     writer: this.writer,
    //     content: this.content,
    //     regdate: regdate,
    //   };

    //   this.$http
    //     .put("http://localhost:3000/board/" + this.boardId, this.form)
    //     .then((res) => {
    //       console.log(res);
    //       if (res.status == 200) {
    //         alert("수정되었습니다.");
    //         this.fnList();
    //       } else {
    //         alert("실행중 실패했습니다.\n다시 이용해 주세요");
    //       }
    //     })
    //     .catch((err) => {
    //       console.log(err);
    //     });
    // },
    // fnDelProc() {
    //   if (confirm("정말 삭제하시겠습니까?") == true) {
    //     this.$http
    //       .delete("http://localhost:3000/board/" + this.boardId)
    //       .then((res) => {
    //         if (res.status == 200) {
    //           alert("삭제되었습니다.");
    //           this.fnList();
    //         } else {
    //           alert("실행중 실패했습니다.\n다시 이용해 주세요");
    //         }
    //       })
    //       .catch((err) => {
    //         console.log(err);
    //       });
    //   } else {
    //     return;
    //   }
    // },
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
  text-align: left;
}
.tbAdd td.txt_cont {
  height: 300px;
  vertical-align: top;
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