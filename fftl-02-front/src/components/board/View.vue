<template>
  <div id="view">
    <h1>게시판 상세보기</h1>
    <!-- 만약 게시글을 조회한 유저가 작성자라면 -->
    <div v-if="uCheck" class="AddWrap">
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
            <td>
              <input
                type="text"
                v-model="nickname"
                ref="nickname"
                readonly="readonly"
              />
            </td>
          </tr>
          <tr>
            <th>날짜</th>
            <td>
              <input
                type="text"
                v-model="regdate"
                ref="regdate"
                readonly="readonly"
              />
            </td>
          </tr>
          <tr>
            <th>내용</th>
            <td><textarea v-model="content" ref="content"></textarea></td>
          </tr>
        </table>
      </form>
    </div>
    <!-- 만약 게시글을 조회한 유저가 작성자가 아니라면 -->
    <div v-else class="AddWrap">
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
            <td>{{ regdate }}</td>
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
      <a href="javascript:;" @click="boardUpdate" class="btnAdd btn">수정</a>
      <a href="javascript:;" @click="boardDelete" class="btnAdd btn">삭제</a>
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
      board_id: this.$route.query.board_id,
      user_id: "",
      title: "",
      nickname: "",
      regdate: "",
      content: "",
      comments: [],
      form: "",
    };
  },

  mounted() {
    this.getOneBoard();
    this.getComments();
  },

  methods: {
    //유저가 작성자인지 아닌지 확인합니다.
    userCheck() {
      if (this.$store.state.user_id == this.user_id) {
        this.uCheck = true;
      }
    },

    getOneBoard() {
      this.$http
        .get("http://127.0.0.1:8080/board/" + this.board_id, {
          headers: { Authorization: this.$store.state.token },
        })
        .then((res) => {
          console.log(res);

          if (res.data.board) {
            this.title = res.data.board.title;
            this.nickname = res.data.board.nickname;
            this.regdate = res.data.board.regdate;
            this.content = res.data.board.content.replace(/(\n)/g, "<br/>");
            this.user_id = res.data.board.user_id;
          } else {
            this.title = res.data.title;
            this.nickname = res.data.nickname;
            this.regdate = res.data.regdate;
            this.content = res.data.content.replace(/(\n)/g, "<br/>");
            this.user_id = res.data.user.user_id;
          }
          this.userCheck();
        })
        .catch((err) => {
          console.log(err);
        });
    },

    goList() {
      // delete this.body.num;
      // this.$router.push({ path: "/board/list", query: this.body });
      this.$router.push({ path: "/board/list", query: this.body });
    },

    getComments() {
      this.$http
        .get("http://127.0.0.1:8080/comment/" + this.board_id, {
          headers: { Authorization: this.$store.state.token },
        })
        .then((res) => {
          console.log(res.data);
          if (res.data.comments) {
            this.comments = res.data.comments;
          } else {
            this.comments = res.data;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    boardUpdate() {
      if (!this.title) {
        alert("제목을 입력해 주세요");
        this.$refs.title.focus(); //방식으로 선택자를 찾는다.
        return;
      }
      this.form = {
        title: this.title,
        nickname: this.nickname,
        content: this.content,
        regdate: this.regdate,
      };
      this.$http
        .patch("http://127.0.0.1:8080/board/" + this.board_id, this.form, {
          headers: { Authorization: this.$store.state.token },
        })
        .then((res) => {
          console.log(res);
          if (res.status == 200) {
            alert("수정되었습니다.");
            this.goList();
          } else {
            alert("실행중 실패했습니다.\n다시 이용해 주세요");
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    boardDelete() {
      if (confirm("정말 삭제하시겠습니까?") == true) {
        this.$http
          .delete("http://127.0.0.1:8080/board/" + this.board_id, {
            headers: { Authorization: this.$store.state.token },
          })
          .then((res) => {
            if (res.status == 200) {
              alert("삭제되었습니다.");
              this.goList();
            } else {
              alert("실행중 실패했습니다.\n다시 이용해 주세요");
            }
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        return;
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
  text-align: left;
}
.tbAdd td.txt_cont {
  height: 300px;
  vertical-align: top;
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
#view {
  position: relative;
}
</style>