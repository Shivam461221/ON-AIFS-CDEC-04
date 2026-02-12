# Complete API Testing Guide - Appointment Management System

## Table of Contents
1. [Setup & Initial Admin Creation](#setup--initial-admin-creation)
2. [Authentication APIs](#authentication-apis)
3. [Admin APIs](#admin-apis)
4. [Doctor APIs](#doctor-apis)
5. [Receptionist APIs](#receptionist-apis)
6. [Public APIs (No Auth)](#public-apis-no-auth)
7. [Testing Flow Sequence](#testing-flow-sequence)

---

## Base URL
```
http://localhost:8080
```

---

## Setup & Initial Admin Creation

### Step 1: Create First Admin (One Time Only)
Since `/api/auth/register` is removed, you need to manually insert the first admin into the database.

**SQL Script to Insert First Admin:**
```sql
INSERT INTO users (full_name, email, password, phone_number, role, is_active, created_at, updated_at)
VALUES (
    'Admin User',
    'admin@hospital.com',
    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhkO', -- password: admin123
    '9876543210',
    'ADMIN',
    true,
    NOW(),
    NOW()
);
```

**Note:** The password hash above corresponds to `admin123` (BCrypt encoded).

---

## Authentication APIs

### 1. Login (Any User)
**Endpoint:** `POST /api/auth/login`  
**Authentication:** None  
**Content-Type:** `application/json`

**Request Body:**
```json
{
  "email": "admin@hospital.com",
  "password": "admin123"
}
```

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "tokenType": "Bearer",
    "userId": 1,
    "fullName": "Admin User",
    "email": "admin@hospital.com",
    "role": "ADMIN",
    "message": "Login successful"
  },
  "timestamp": "2024-02-05T10:30:00"
}
```

**Copy the `token` value and use it in subsequent requests!**

---

### 2. Get Current User Profile
**Endpoint:** `GET /api/auth/me`  
**Authentication:** Required  
**Headers:**
```
Authorization: Bearer YOUR_JWT_TOKEN_HERE
```

**Response (200 OK):**
```json
{
  "success": true,
  "message": "User details fetched successfully",
  "data": {
    "id": 1,
    "fullName": "Admin User",
    "email": "admin@hospital.com",
    "phoneNumber": "9876543210",
    "role": "ADMIN",
    "isActive": true,
    "createdById": null,
    "createdByName": null,
    "createdAt": "2024-02-05T10:00:00",
    "updatedAt": "2024-02-05T10:00:00"
  },
  "timestamp": "2024-02-05T10:35:00"
}
```

---

### 3. Change Own Password
**Endpoint:** `PUT /api/auth/change-password`  
**Authentication:** Required  
**Headers:**
```
Authorization: Bearer YOUR_JWT_TOKEN_HERE
```

**Query Parameters:**
```
oldPassword=admin123
newPassword=newAdmin456
```

**Full URL Example:**
```
PUT http://localhost:8080/api/auth/change-password?oldPassword=admin123&newPassword=newAdmin456
```

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Password changed successfully",
  "data": null,
  "timestamp": "2024-02-05T10:40:00"
}
```

---

## Admin APIs

**All Admin endpoints require:**
```
Authorization: Bearer ADMIN_JWT_TOKEN
```

### USER MANAGEMENT

#### 1. Create User (Admin/Doctor/Receptionist)
**Endpoint:** `POST /api/admin/users`

**Request Body (Create Doctor):**
```json
{
  "fullName": "Dr. Rajesh Kumar",
  "email": "rajesh.kumar@hospital.com",
  "password": "doctor123",
  "phoneNumber": "9876543211",
  "role": "DOCTOR"
}
```

**Request Body (Create Receptionist):**
```json
{
  "fullName": "Priya Sharma",
  "email": "priya.sharma@hospital.com",
  "password": "recept123",
  "phoneNumber": "9876543212",
  "role": "RECEPTIONIST"
}
```

**Request Body (Create Another Admin):**
```json
{
  "fullName": "Admin Two",
  "email": "admin2@hospital.com",
  "password": "admin456",
  "phoneNumber": "9876543213",
  "role": "ADMIN"
}
```

**Response (201 Created):**
```json
{
  "success": true,
  "message": "User created successfully",
  "data": {
    "id": 2,
    "fullName": "Dr. Rajesh Kumar",
    "email": "rajesh.kumar@hospital.com",
    "phoneNumber": "9876543211",
    "role": "DOCTOR",
    "isActive": true,
    "createdById": 1,
    "createdByName": "Admin User",
    "createdAt": "2024-02-05T11:00:00",
    "updatedAt": "2024-02-05T11:00:00"
  },
  "timestamp": "2024-02-05T11:00:00"
}
```

---

#### 2. Get All Users
**Endpoint:** `GET /api/admin/users`

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Users fetched successfully",
  "data": [
    {
      "id": 1,
      "fullName": "Admin User",
      "email": "admin@hospital.com",
      "phoneNumber": "9876543210",
      "role": "ADMIN",
      "isActive": true,
      "createdById": null,
      "createdByName": null,
      "createdAt": "2024-02-05T10:00:00",
      "updatedAt": "2024-02-05T10:00:00"
    },
    {
      "id": 2,
      "fullName": "Dr. Rajesh Kumar",
      "email": "rajesh.kumar@hospital.com",
      "phoneNumber": "9876543211",
      "role": "DOCTOR",
      "isActive": true,
      "createdById": 1,
      "createdByName": "Admin User",
      "createdAt": "2024-02-05T11:00:00",
      "updatedAt": "2024-02-05T11:00:00"
    }
  ],
  "timestamp": "2024-02-05T11:05:00"
}
```

---

#### 3. Get Users by Role
**Endpoint:** `GET /api/admin/users/role/{role}`

**Example:** `GET /api/admin/users/role/DOCTOR`

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Users fetched successfully",
  "data": [
    {
      "id": 2,
      "fullName": "Dr. Rajesh Kumar",
      "email": "rajesh.kumar@hospital.com",
      "phoneNumber": "9876543211",
      "role": "DOCTOR",
      "isActive": true,
      "createdById": 1,
      "createdByName": "Admin User",
      "createdAt": "2024-02-05T11:00:00",
      "updatedAt": "2024-02-05T11:00:00"
    }
  ],
  "timestamp": "2024-02-05T11:10:00"
}
```

---

#### 4. Get Single User by ID
**Endpoint:** `GET /api/admin/users/{id}`

**Example:** `GET /api/admin/users/2`

**Response (200 OK):**
```json
{
  "success": true,
  "message": "User fetched successfully",
  "data": {
    "id": 2,
    "fullName": "Dr. Rajesh Kumar",
    "email": "rajesh.kumar@hospital.com",
    "phoneNumber": "9876543211",
    "role": "DOCTOR",
    "isActive": true,
    "createdById": 1,
    "createdByName": "Admin User",
    "createdAt": "2024-02-05T11:00:00",
    "updatedAt": "2024-02-05T11:00:00"
  },
  "timestamp": "2024-02-05T11:15:00"
}
```

---

#### 5. Update User
**Endpoint:** `PUT /api/admin/users/{id}`

**Example:** `PUT /api/admin/users/2`

**Request Body (all fields optional):**
```json
{
  "fullName": "Dr. Rajesh Kumar (Updated)",
  "phoneNumber": "9999999999",
  "password": "newPassword123"
}
```

**Response (200 OK):**
```json
{
  "success": true,
  "message": "User updated successfully",
  "data": {
    "id": 2,
    "fullName": "Dr. Rajesh Kumar (Updated)",
    "email": "rajesh.kumar@hospital.com",
    "phoneNumber": "9999999999",
    "role": "DOCTOR",
    "isActive": true,
    "createdById": 1,
    "createdByName": "Admin User",
    "createdAt": "2024-02-05T11:00:00",
    "updatedAt": "2024-02-05T11:20:00"
  },
  "timestamp": "2024-02-05T11:20:00"
}
```

---

#### 6. Deactivate User
**Endpoint:** `DELETE /api/admin/users/{id}`

**Example:** `DELETE /api/admin/users/3`

**Response (200 OK):**
```json
{
  "success": true,
  "message": "User deactivated successfully",
  "data": null,
  "timestamp": "2024-02-05T11:25:00"
}
```

---

#### 7. Reactivate User
**Endpoint:** `PATCH /api/admin/users/{id}/reactivate`

**Example:** `PATCH /api/admin/users/3/reactivate`

**Response (200 OK):**
```json
{
  "success": true,
  "message": "User reactivated successfully",
  "data": null,
  "timestamp": "2024-02-05T11:30:00"
}
```

---

### PATIENT MANAGEMENT

#### 8. Create Patient
**Endpoint:** `POST /api/admin/patients`

**Request Body:**
```json
{
  "fullName": "Amit Verma",
  "email": "amit.verma@gmail.com",
  "phoneNumber": "9123456780",
  "dateOfBirth": "1990-05-15",
  "gender": "MALE",
  "address": "123 MG Road, Indore, MP, India",
  "medicalHistory": "Type 2 Diabetes, Hypertension"
}
```

**Response (201 Created):**
```json
{
  "success": true,
  "message": "Patient created successfully",
  "data": {
    "id": 1,
    "fullName": "Amit Verma",
    "email": "amit.verma@gmail.com",
    "phoneNumber": "9123456780",
    "dateOfBirth": "1990-05-15",
    "gender": "MALE",
    "address": "123 MG Road, Indore, MP, India",
    "medicalHistory": "Type 2 Diabetes, Hypertension",
    "registeredById": 1,
    "registeredByName": "Admin User",
    "createdAt": "2024-02-05T12:00:00",
    "updatedAt": "2024-02-05T12:00:00"
  },
  "timestamp": "2024-02-05T12:00:00"
}
```

---

#### 9. Get All Patients
**Endpoint:** `GET /api/admin/patients`

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Patients fetched successfully",
  "data": [
    {
      "id": 1,
      "fullName": "Amit Verma",
      "email": "amit.verma@gmail.com",
      "phoneNumber": "9123456780",
      "dateOfBirth": "1990-05-15",
      "gender": "MALE",
      "address": "123 MG Road, Indore, MP, India",
      "medicalHistory": "Type 2 Diabetes, Hypertension",
      "registeredById": 1,
      "registeredByName": "Admin User",
      "createdAt": "2024-02-05T12:00:00",
      "updatedAt": "2024-02-05T12:00:00"
    }
  ],
  "timestamp": "2024-02-05T12:05:00"
}
```

---

#### 10. Get Patient by ID
**Endpoint:** `GET /api/admin/patients/{id}`

**Example:** `GET /api/admin/patients/1`

---

#### 11. Update Patient
**Endpoint:** `PUT /api/admin/patients/{id}`

**Example:** `PUT /api/admin/patients/1`

**Request Body:**
```json
{
  "phoneNumber": "9999888877",
  "medicalHistory": "Type 2 Diabetes, Hypertension, Recently diagnosed with Arthritis"
}
```

---

#### 12. Delete Patient
**Endpoint:** `DELETE /api/admin/patients/{id}`

**Example:** `DELETE /api/admin/patients/1`

---

### APPOINTMENT MANAGEMENT

#### 13. Get All Appointments
**Endpoint:** `GET /api/admin/appointments`

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Appointments fetched successfully",
  "data": [
    {
      "id": 1,
      "patientId": 1,
      "patientName": "Amit Verma",
      "patientEmail": "amit.verma@gmail.com",
      "patientPhone": "9123456780",
      "doctorId": 2,
      "doctorName": "Dr. Rajesh Kumar",
      "doctorEmail": "rajesh.kumar@hospital.com",
      "appointmentDate": "2024-02-10",
      "appointmentTime": "10:00:00",
      "status": "PENDING",
      "reasonForVisit": "Regular checkup",
      "notes": null,
      "diagnosis": null,
      "prescription": null,
      "createdAt": "2024-02-05T13:00:00",
      "updatedAt": "2024-02-05T13:00:00"
    }
  ],
  "timestamp": "2024-02-05T13:05:00"
}
```

---

#### 14. Get Appointment by ID
**Endpoint:** `GET /api/admin/appointments/{id}`

**Example:** `GET /api/admin/appointments/1`

---

#### 15. Cancel Appointment
**Endpoint:** `PATCH /api/admin/appointments/{id}/cancel`

**Example:** `PATCH /api/admin/appointments/1/cancel`

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Appointment cancelled successfully",
  "data": {
    "id": 1,
    "patientId": 1,
    "patientName": "Amit Verma",
    "patientEmail": "amit.verma@gmail.com",
    "patientPhone": "9123456780",
    "doctorId": 2,
    "doctorName": "Dr. Rajesh Kumar",
    "doctorEmail": "rajesh.kumar@hospital.com",
    "appointmentDate": "2024-02-10",
    "appointmentTime": "10:00:00",
    "status": "CANCELLED",
    "reasonForVisit": "Regular checkup",
    "notes": null,
    "diagnosis": null,
    "prescription": null,
    "createdAt": "2024-02-05T13:00:00",
    "updatedAt": "2024-02-05T13:10:00"
  },
  "timestamp": "2024-02-05T13:10:00"
}
```

---

## Doctor APIs

**All Doctor endpoints require:**
```
Authorization: Bearer DOCTOR_JWT_TOKEN
```

**First, login as Doctor to get token:**
```json
POST /api/auth/login
{
  "email": "rajesh.kumar@hospital.com",
  "password": "doctor123"
}
```

### RECEPTIONIST MANAGEMENT

#### 1. Create Receptionist
**Endpoint:** `POST /api/doctor/receptionists`

**Request Body:**
```json
{
  "fullName": "Sneha Patel",
  "email": "sneha.patel@hospital.com",
  "password": "recept456",
  "phoneNumber": "9111222333",
  "role": "RECEPTIONIST"
}
```

**Note:** Role MUST be "RECEPTIONIST" — if you try "ADMIN" or "DOCTOR", you'll get an error.

**Response (201 Created):**
```json
{
  "success": true,
  "message": "Receptionist registered successfully",
  "data": {
    "id": 4,
    "fullName": "Sneha Patel",
    "email": "sneha.patel@hospital.com",
    "phoneNumber": "9111222333",
    "role": "RECEPTIONIST",
    "isActive": true,
    "createdById": 2,
    "createdByName": "Dr. Rajesh Kumar",
    "createdAt": "2024-02-05T14:00:00",
    "updatedAt": "2024-02-05T14:00:00"
  },
  "timestamp": "2024-02-05T14:00:00"
}
```

---

#### 2. Get My Receptionists
**Endpoint:** `GET /api/doctor/receptionists`

**Response:** List of only receptionists created by this doctor.

---

#### 3. Get Receptionist by ID
**Endpoint:** `GET /api/doctor/receptionists/{id}`

**Example:** `GET /api/doctor/receptionists/4`

---

#### 4. Update Receptionist
**Endpoint:** `PUT /api/doctor/receptionists/{id}`

**Example:** `PUT /api/doctor/receptionists/4`

**Request Body:**
```json
{
  "fullName": "Sneha Patel (Senior Receptionist)",
  "phoneNumber": "9999111222"
}
```

---

#### 5. Deactivate Receptionist
**Endpoint:** `DELETE /api/doctor/receptionists/{id}`

**Example:** `DELETE /api/doctor/receptionists/4`

---

### PATIENT MANAGEMENT

#### 6. Create Patient
**Endpoint:** `POST /api/doctor/patients`

**Request Body:**
```json
{
  "fullName": "Sanjay Gupta",
  "email": "sanjay.gupta@gmail.com",
  "phoneNumber": "9988776655",
  "dateOfBirth": "1985-08-20",
  "gender": "MALE",
  "address": "456 AB Road, Indore, MP, India",
  "medicalHistory": "Asthma"
}
```

**Response (201 Created):**
```json
{
  "success": true,
  "message": "Patient registered successfully",
  "data": {
    "id": 2,
    "fullName": "Sanjay Gupta",
    "email": "sanjay.gupta@gmail.com",
    "phoneNumber": "9988776655",
    "dateOfBirth": "1985-08-20",
    "gender": "MALE",
    "address": "456 AB Road, Indore, MP, India",
    "medicalHistory": "Asthma",
    "registeredById": 2,
    "registeredByName": "Dr. Rajesh Kumar",
    "createdAt": "2024-02-05T14:30:00",
    "updatedAt": "2024-02-05T14:30:00"
  },
  "timestamp": "2024-02-05T14:30:00"
}
```

---

#### 7. Get My Patients
**Endpoint:** `GET /api/doctor/patients`

**Response:** List of only patients registered by this doctor.

---

#### 8. Get Patient by ID
**Endpoint:** `GET /api/doctor/patients/{id}`

**Example:** `GET /api/doctor/patients/2`

---

#### 9. Update Patient
**Endpoint:** `PUT /api/doctor/patients/{id}`

**Example:** `PUT /api/doctor/patients/2`

---

#### 10. Delete Patient
**Endpoint:** `DELETE /api/doctor/patients/{id}`

**Example:** `DELETE /api/doctor/patients/2`

---

### APPOINTMENT MANAGEMENT

#### 11. Get My Appointments
**Endpoint:** `GET /api/doctor/appointments`

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Appointments fetched successfully",
  "data": [
    {
      "id": 2,
      "patientId": 2,
      "patientName": "Sanjay Gupta",
      "patientEmail": "sanjay.gupta@gmail.com",
      "patientPhone": "9988776655",
      "doctorId": 2,
      "doctorName": "Dr. Rajesh Kumar",
      "doctorEmail": "rajesh.kumar@hospital.com",
      "appointmentDate": "2024-02-12",
      "appointmentTime": "14:00:00",
      "status": "PENDING",
      "reasonForVisit": "Asthma checkup",
      "notes": null,
      "diagnosis": null,
      "prescription": null,
      "createdAt": "2024-02-05T15:00:00",
      "updatedAt": "2024-02-05T15:00:00"
    }
  ],
  "timestamp": "2024-02-05T15:05:00"
}
```

---

#### 12. Get Appointment by ID
**Endpoint:** `GET /api/doctor/appointments/{id}`

**Example:** `GET /api/doctor/appointments/2`

---

#### 13. Update Appointment Status
**Endpoint:** `PATCH /api/doctor/appointments/{id}/status`

**Example:** `PATCH /api/doctor/appointments/2/status`

**Request Body:**
```json
{
  "status": "CONFIRMED"
}
```

**Valid Status Transitions:**
- `PENDING` → `CONFIRMED`, `CANCELLED`
- `CONFIRMED` → `COMPLETED`, `CANCELLED`, `NO_SHOW`

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Appointment status updated successfully",
  "data": {
    "id": 2,
    "status": "CONFIRMED",
    ...
  },
  "timestamp": "2024-02-05T15:10:00"
}
```

---

#### 14. Add Clinical Details
**Endpoint:** `PATCH /api/doctor/appointments/{id}/clinical`

**Example:** `PATCH /api/doctor/appointments/2/clinical`

**Request Body:**
```json
{
  "notes": "Patient reports difficulty breathing in the morning",
  "diagnosis": "Mild Asthma exacerbation",
  "prescription": "Salbutamol inhaler 2 puffs twice daily, Montelukast 10mg once daily"
}
```

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Clinical details updated successfully",
  "data": {
    "id": 2,
    "notes": "Patient reports difficulty breathing in the morning",
    "diagnosis": "Mild Asthma exacerbation",
    "prescription": "Salbutamol inhaler 2 puffs twice daily, Montelukast 10mg once daily",
    ...
  },
  "timestamp": "2024-02-05T15:15:00"
}
```

---

#### 15. Cancel Appointment
**Endpoint:** `PATCH /api/doctor/appointments/{id}/cancel`

**Example:** `PATCH /api/doctor/appointments/2/cancel`

---

## Receptionist APIs

**All Receptionist endpoints require:**
```
Authorization: Bearer RECEPTIONIST_JWT_TOKEN
```

**First, login as Receptionist:**
```json
POST /api/auth/login
{
  "email": "sneha.patel@hospital.com",
  "password": "recept456"
}
```

### PATIENT MANAGEMENT ONLY

#### 1. Create Patient
**Endpoint:** `POST /api/receptionist/patients`

**Request Body:**
```json
{
  "fullName": "Meera Joshi",
  "email": "meera.joshi@gmail.com",
  "phoneNumber": "9876543219",
  "dateOfBirth": "1995-03-10",
  "gender": "FEMALE",
  "address": "789 Vijay Nagar, Indore, MP, India",
  "medicalHistory": "No significant medical history"
}
```

**Response (201 Created):**
```json
{
  "success": true,
  "message": "Patient registered successfully",
  "data": {
    "id": 3,
    "fullName": "Meera Joshi",
    "email": "meera.joshi@gmail.com",
    "phoneNumber": "9876543219",
    "dateOfBirth": "1995-03-10",
    "gender": "FEMALE",
    "address": "789 Vijay Nagar, Indore, MP, India",
    "medicalHistory": "No significant medical history",
    "registeredById": 4,
    "registeredByName": "Sneha Patel",
    "createdAt": "2024-02-05T16:00:00",
    "updatedAt": "2024-02-05T16:00:00"
  },
  "timestamp": "2024-02-05T16:00:00"
}
```

---

#### 2. Get My Patients
**Endpoint:** `GET /api/receptionist/patients`

**Response:** Only patients registered by this receptionist.

---

#### 3. Get Patient by ID
**Endpoint:** `GET /api/receptionist/patients/{id}`

**Example:** `GET /api/receptionist/patients/3`

---

#### 4. Update Patient
**Endpoint:** `PUT /api/receptionist/patients/{id}`

**Example:** `PUT /api/receptionist/patients/3`

**Request Body:**
```json
{
  "phoneNumber": "9111000888"
}
```

---

#### 5. Delete Patient
**Endpoint:** `DELETE /api/receptionist/patients/{id}`

**Example:** `DELETE /api/receptionist/patients/3`

---

## Public APIs (No Auth)

**No Authorization header needed for these endpoints!**

### 1. Book Appointment (No Login Required)
**Endpoint:** `POST /api/public/appointments`

**Request Body:**
```json
{
  "patientName": "Rakesh Mehta",
  "patientEmail": "rakesh.mehta@gmail.com",
  "patientPhone": "9123456789",
  "doctorId": 2,
  "appointmentDate": "2024-02-15",
  "appointmentTime": "11:00:00",
  "reasonForVisit": "Fever and cough for 3 days"
}
```

**Response (201 Created):**
```json
{
  "success": true,
  "message": "Appointment booked successfully! Your appointment ID is: 3",
  "data": {
    "id": 3,
    "patientId": 4,
    "patientName": "Rakesh Mehta",
    "patientEmail": "rakesh.mehta@gmail.com",
    "patientPhone": "9123456789",
    "doctorId": 2,
    "doctorName": "Dr. Rajesh Kumar",
    "doctorEmail": "rajesh.kumar@hospital.com",
    "appointmentDate": "2024-02-15",
    "appointmentTime": "11:00:00",
    "status": "PENDING",
    "reasonForVisit": "Fever and cough for 3 days",
    "notes": null,
    "diagnosis": null,
    "prescription": null,
    "createdAt": "2024-02-05T17:00:00",
    "updatedAt": "2024-02-05T17:00:00"
  },
  "timestamp": "2024-02-05T17:00:00"
}
```

**Note:** If patient email doesn't exist, a new patient record is auto-created!

---

### 2. Get All Available Doctors
**Endpoint:** `GET /api/public/doctors`

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Available doctors fetched successfully",
  "data": [
    {
      "id": 2,
      "fullName": "Dr. Rajesh Kumar",
      "email": "rajesh.kumar@hospital.com",
      "phoneNumber": "9876543211",
      "role": "DOCTOR",
      "isActive": true,
      "createdById": 1,
      "createdByName": "Admin User",
      "createdAt": "2024-02-05T11:00:00",
      "updatedAt": "2024-02-05T11:00:00"
    }
  ],
  "timestamp": "2024-02-05T17:05:00"
}
```

---

### 3. Get Doctor's Booked Slots for a Date
**Endpoint:** `GET /api/public/doctors/{doctorId}/slots?date=YYYY-MM-DD`

**Example:** `GET /api/public/doctors/2/slots?date=2024-02-15`

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Booked slots fetched successfully",
  "data": [
    {
      "id": 3,
      "appointmentDate": "2024-02-15",
      "appointmentTime": "11:00:00",
      "status": "PENDING",
      ...
    }
  ],
  "timestamp": "2024-02-05T17:10:00"
}
```

**Use this to show unavailable time slots to patients when booking!**

---

## Testing Flow Sequence

### Complete End-to-End Testing Flow

```
1. INSERT FIRST ADMIN via SQL
   ↓
2. LOGIN as Admin → get ADMIN_TOKEN
   ↓
3. Admin creates Doctor → get Doctor ID
   ↓
4. LOGIN as Doctor → get DOCTOR_TOKEN
   ↓
5. Doctor creates Receptionist
   ↓
6. LOGIN as Receptionist → get RECEPTIONIST_TOKEN
   ↓
7. Doctor creates Patient (Patient ID = 2)
   ↓
8. Receptionist creates Patient (Patient ID = 3)
   ↓
9. PUBLIC: Book appointment with Doctor (no login, auto-creates Patient ID = 4)
   ↓
10. PUBLIC: Get available doctors
   ↓
11. PUBLIC: Check booked slots for doctor on a date
   ↓
12. DOCTOR: View all my appointments
   ↓
13. DOCTOR: Update appointment status PENDING → CONFIRMED
   ↓
14. DOCTOR: Add clinical details (notes, diagnosis, prescription)
   ↓
15. DOCTOR: Update status CONFIRMED → COMPLETED
   ↓
16. ADMIN: View all appointments
   ↓
17. ADMIN: Cancel any appointment
```

---

## Common Error Responses

### 400 Bad Request (Validation Error)
```json
{
  "success": false,
  "message": "Validation failed",
  "data": {
    "email": "Please provide a valid email",
    "phoneNumber": "Phone number must be exactly 10 digits"
  },
  "timestamp": "2024-02-05T18:00:00"
}
```

### 401 Unauthorized
```json
{
  "success": false,
  "message": "Invalid email or password",
  "data": null,
  "timestamp": "2024-02-05T18:05:00"
}
```

### 403 Forbidden
```json
{
  "status": 403,
  "error": "Forbidden",
  "message": "Access Denied",
  "path": "/api/admin/users"
}
```

### 404 Not Found
```json
{
  "success": false,
  "message": "User not found with ID: 999",
  "data": null,
  "timestamp": "2024-02-05T18:10:00"
}
```

### 409 Conflict
```json
{
  "success": false,
  "message": "Email already in use: test@example.com",
  "data": null,
  "timestamp": "2024-02-05T18:15:00"
}
```

---

## Postman Environment Variables

Create these variables in Postman for easier testing:

```
BASE_URL = http://localhost:8080
ADMIN_TOKEN = (paste after admin login)
DOCTOR_TOKEN = (paste after doctor login)
RECEPTIONIST_TOKEN = (paste after receptionist login)
DOCTOR_ID = 2
PATIENT_ID = 1
APPOINTMENT_ID = 1
```

Then use in requests like:
```
{{BASE_URL}}/api/admin/users
Authorization: Bearer {{ADMIN_TOKEN}}
```

---

## Quick Reference - All Endpoints

### Auth (Public)
- POST   `/api/auth/login`
- GET    `/api/auth/me` (requires auth)
- PUT    `/api/auth/change-password` (requires auth)

### Admin (ADMIN role required)
**Users:**
- POST   `/api/admin/users`
- GET    `/api/admin/users`
- GET    `/api/admin/users/role/{role}`
- GET    `/api/admin/users/{id}`
- PUT    `/api/admin/users/{id}`
- DELETE `/api/admin/users/{id}`
- PATCH  `/api/admin/users/{id}/reactivate`

**Patients:**
- POST   `/api/admin/patients`
- GET    `/api/admin/patients`
- GET    `/api/admin/patients/{id}`
- PUT    `/api/admin/patients/{id}`
- DELETE `/api/admin/patients/{id}`

**Appointments:**
- GET    `/api/admin/appointments`
- GET    `/api/admin/appointments/{id}`
- PATCH  `/api/admin/appointments/{id}/cancel`

### Doctor (DOCTOR role required)
**Receptionists:**
- POST   `/api/doctor/receptionists`
- GET    `/api/doctor/receptionists`
- GET    `/api/doctor/receptionists/{id}`
- PUT    `/api/doctor/receptionists/{id}`
- DELETE `/api/doctor/receptionists/{id}`

**Patients:**
- POST   `/api/doctor/patients`
- GET    `/api/doctor/patients`
- GET    `/api/doctor/patients/{id}`
- PUT    `/api/doctor/patients/{id}`
- DELETE `/api/doctor/patients/{id}`

**Appointments:**
- GET    `/api/doctor/appointments`
- GET    `/api/doctor/appointments/{id}`
- PATCH  `/api/doctor/appointments/{id}/status`
- PATCH  `/api/doctor/appointments/{id}/clinical`
- PATCH  `/api/doctor/appointments/{id}/cancel`

### Receptionist (RECEPTIONIST role required)
**Patients:**
- POST   `/api/receptionist/patients`
- GET    `/api/receptionist/patients`
- GET    `/api/receptionist/patients/{id}`
- PUT    `/api/receptionist/patients/{id}`
- DELETE `/api/receptionist/patients/{id}`

### Public (No auth required)
- POST   `/api/public/appointments`
- GET    `/api/public/doctors`
- GET    `/api/public/doctors/{doctorId}/slots?date=YYYY-MM-DD`

---

**Total Endpoints: 48**

✅ Complete API documentation with dummy data ready for Postman testing!