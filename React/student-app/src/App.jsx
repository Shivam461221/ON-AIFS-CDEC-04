import { useRef, useState } from 'react'
import './App.css'
import StudentList from './StudentList.jsx'
import List from './Components/List.jsx';
import RegistrationForm from './Components/RegistrationForm.jsx';
import Header from './Components/Header.jsx';

function App() {
  const [students, setStudents] = useState(StudentList);
  const [branch, setBranch] = useState('All');
  const idRef = useRef();
  const nameRef = useRef();
  const courseRef = useRef();
  const branchRef = useRef();

  const filteredStudents = (branch === 'All') ? students : students.filter((student) => student.studentBranch === branch);

  // const addStudent = () => {
  //   let studentId = document.getElementById('studentId').value;
  //   let studentName = document.getElementById('studentName').value;
  //   let studentCourse = document.getElementById('studentCourse').value;
  //   let studentBranch = document.getElementById('studentBranch').value;

  //   if (studentId && studentName && studentCourse && studentBranch) {
  //     const newStudent = { studentId, studentName, studentCourse, studentBranch };

  //     setStudents([...students, newStudent]);

  //     document.getElementById('studentId').value = '';
  //     document.getElementById('studentName').value = '';
  //     document.getElementById('studentCourse').value = '';
  //     document.getElementById('studentBranch').value = '';
  //   }
  //   else {
  //     alert('Please Enter Data First');
  //   }

  // }

  const addStudent = () => {
    let studentId = idRef.current.value;
    let studentName = nameRef.current.value;
    let studentCourse = courseRef.current.value;
    let studentBranch = branchRef.current.value;

    if (studentId && studentName && studentCourse && studentBranch) {
      const newStudent = { studentId, studentName, studentCourse, studentBranch };

      setStudents([...students, newStudent]);

      idRef.current.value = '';
      nameRef.current.value = '';
      courseRef.current.value = '';
      branchRef.current.value = '';
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
      <Header/>
      <RegistrationForm idRef={idRef} nameRef={nameRef} courseRef={courseRef} branchRef={branchRef} addStudent={addStudent} />
      <List filteredStudents={filteredStudents} removeStudent={removeStudent} />
      
    </>
  )
}

export default App
