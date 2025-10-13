export default function RegistrationForm({idRef, nameRef, courseRef, branchRef, addStudent}){

    return<>
        <div className="container">
        <div className="row my-2">
          <div className="col-md-6">
            <div className="form-group">
              <label htmlFor="" className="form-label">Stduent Id</label>
              <input ref={idRef} id='studentId' type="text" className="form-control" />
            </div>
            <div className="form-group">
              <label htmlFor="" className="form-label">Stduent Name</label>
              <input ref={nameRef} id='studentName' type="text" className="form-control" />
            </div>
          </div>
          <div className="col-md-6">
            <div className="form-group">
              <label htmlFor="" className="form-label">Stduent Course</label>
              <input ref={courseRef} id='studentCourse' type="text" className="form-control" />
            </div>
            <div className="form-group">
              <label htmlFor="" className="form-label">Stduent Branch</label>
              <input ref={branchRef} id='studentBranch' type="text" className="form-control" />
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
    </>
}