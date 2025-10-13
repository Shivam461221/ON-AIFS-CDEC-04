
export default function List({filteredStudents, removeStudent}){

    return <>
        <table className="table mt-2">
        <thead>
          <tr className='table-danger'>
            <th>S.no</th>
            <th>Student Id</th>
            <th>Name</th>
            <th>Course</th>
            <th>Branch</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {filteredStudents.map((student, index) => <tr key={index} >
            <td>{index + 1}</td>
            <td>{student.studentId}</td>
            <td>{student.studentName}</td>
            <td>{student.studentCourse}</td>
            <td>{student.studentBranch}</td>
            <td><button onClick={() => removeStudent(student.studentId)} className='btn btn-danger'>Remove</button></td>
          </tr>)
          }
        </tbody>
      </table>
    </>
}