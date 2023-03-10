export class Course {
    courseCode: string;
    name: string;
    instructor: string | null;
    numStudents: number;
    type: number;
    semester: number;
    fee: number;

    constructor(courseCode: string, name: string, instructor: string, numStudents: number, type: number, semester: number, fee: number) {
        this.courseCode = courseCode;
        this.name = name;
        this.instructor = null;
        this.numStudents = numStudents;
        this.type = type;
        this.semester = semester;
        this.fee = fee;
    }
}
