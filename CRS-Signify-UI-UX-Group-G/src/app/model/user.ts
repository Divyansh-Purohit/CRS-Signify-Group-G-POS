export class User {

    doj = new Date().toString();

    constructor(
        public roleid: number,
        public userId: string,
        public username: string,
        public address: string,
        public password: string,
    ) { }
}