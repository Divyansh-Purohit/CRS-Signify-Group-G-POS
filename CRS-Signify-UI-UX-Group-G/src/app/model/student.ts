export class Student {
    public studentId: string;
    public age: number;
    public isApproved: number;
    public studentid: string;
    public fname: string;
    public bloodgroup: string;
    public phnum: string;
    public name: string;
    public branch: string;

    constructor(
        studentId: string,
        age: number,
        isApproved: number,
        studentid: string,
        fname: string,
        bloodgroup: string,
        phnum: string,
        name: string,
        branch: string,
        userid: string,
        username: string,
        address: string,
        password: string,
        doj: string,
        roleid: number
    ) {
        this.studentId = studentId;
        this.age = age;
        this.isApproved = isApproved;
        this.studentid = studentid;
        this.fname = fname;
        this.bloodgroup = bloodgroup;
        this.phnum = phnum;
        this.name = name;
        this.branch = branch;
    }
}