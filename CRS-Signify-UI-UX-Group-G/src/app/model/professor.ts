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
