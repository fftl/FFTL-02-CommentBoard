import { User } from "../../entities/user.entity";

export class GetOneBoard {
  title: string;
  content: string;
  nickname: string;
  bregdate: string;
  uid: number;

  constructor(
    title: string,
    content: string,
    nickname: string,
    bregdate: string,
    uid: number,
  ) {
      this.title = title;
      this.content = content;
      this.nickname = nickname;
      this.bregdate = bregdate;
      this.uid = uid;
  }
}
