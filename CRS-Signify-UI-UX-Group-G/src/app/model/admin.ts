export class Admin {
    userId: string;
    username: string;

    constructor(userId: string, username: string) {
        this.userId = userId;
        this.username = username;
    }
}

export class FormAdmin {
    userId: string;
    username: string;
    password: string;
    address: string;
    doj: Date;
    constructor(userId: string, username: string, password: string, address: string, doj: Date) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.address = address;
        this.doj = doj;
    }
}
