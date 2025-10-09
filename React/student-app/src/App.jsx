import { useState } from 'react'
import './App.css'
import stduentList from './StudentList.jsx'
import StudentList from './StudentList.jsx'

function App() {
  const [students, setStudents] = useState(StudentList);
  const [branch, setBranch] = useState('All');

  const filteredStudents = (branch === 'All') ? students : students.filter((student) => student.studentBranch === branch);

  const addStudent = () => {
    let studentId = document.getElementById('studentId').value;
    let studentName = document.getElementById('studentName').value;
    let studentCourse = document.getElementById('studentCourse').value;
    let studentBranch = document.getElementById('studentBranch').value;

    if (studentId && studentName && studentCourse && studentBranch) {
      const newStudent = { studentId, studentName, studentCourse, studentBranch };

      setStudents([...students, newStudent]);

      document.getElementById('studentId').value = '';
      document.getElementById('studentName').value = '';
      document.getElementById('studentCourse').value = '';
      document.getElementById('studentBranch').value = '';
    }
    else {
      alert('Please Enter Data First');
    }

  }

  const removeStudent = (id) => {
    let studentIndex = students.findIndex(student => student.studentId === id);
    students.splice(studentIndex, 1);
    setStudents([...students]);
  }

  return (
    <>
      <div className="container-fluid bg-danger text-center display-6 text-white p-3">
        Student App
      </div>
      <div className="container">
        <div className="row my-2">
          <div className="col-md-6">
            <div className="form-group">
              <label htmlFor="" className="form-label">Stduent Id</label>
              <input id='studentId' type="text" className="form-control" />
            </div>
            <div className="form-group">
              <label htmlFor="" className="form-label">Stduent Name</label>
              <input id='studentName' type="text" className="form-control" />
            </div>
          </div>
          <div className="col-md-6">
            <div className="form-group">
              <label htmlFor="" className="form-label">Stduent Course</label>
              <input id='studentCourse' type="text" className="form-control" />
            </div>
            <div className="form-group">
              <label htmlFor="" className="form-label">Stduent Branch</label>
              <input id='studentBranch' type="text" className="form-control" />
            </div>
          </div>
        </div>
        <button onClick={addStudent} className="btn btn-primary">Add new</button>
        <div>
          <button onClick={() => setBranch('All')} className="btn btn-warning m-2">All</button>
          <button onClick={() => setBranch('CS')} className="btn btn-secondary m-2">CS</button>
          <button onClick={() => setBranch('CSE')} className="btn btn-info m-2">CSE</button>
          <button onClick={() => setBranch('EC')} className="btn btn-danger m-2">EC</button>
          <button onClick={() => setBranch('CIVIL')} className="btn btn-dark m-2">Civil</button>
        </div>
      </div>
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
  )
}

export default App
