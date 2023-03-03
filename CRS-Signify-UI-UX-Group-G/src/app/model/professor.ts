export class Professor {
    userId: string;
    username: string;
    department: string;
    designation: string;
    courseTaught: string;

    constructor(
        userId: string,
        username: string,
        department: string,
        designation: string,
        courseTaught: string,

    ) {
        this.userId = userId;
        this.department = department;
        this.designation = designation;
        this.username = username;
        this.courseTaught = courseTaught;
    }
}


export class FormProfessor {
    userId: string;
    username: string;
    password: string;
    address: string;
    doj: Date;
    roleid: number;
    department: string;
    designation: string;
    courseTaught: string;

    constructor(
        userId: string,
        username: string,
        password: string,
        address: string,
        doj: Date,
        roleid: number,
        department: string,
        designation: string,
        courseTaught: string,
    ) {
        this.userId = "123";
        this.department = department;
        this.designation = designation;
        this.username = username;
        this.courseTaught = "NULL";
        this.doj = doj;
        this.roleid = roleid;
        this.password = password;
        this.address = address;
    }
}