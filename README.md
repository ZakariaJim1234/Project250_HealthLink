# HealthLink

> This is the GitHub repository for Project 250.

**Team Members:**
- Zakaria Ahmed Jim (2020331050)
- Ibnul Mansib (2020331061)
---

## About the Project

HealthLink is an Android mobile application that provides quick access to healthcare information in the Sylhet region. The app has two distinct modes: a **User mode** for the general public to browse hospitals and doctors, and a **HealthCenter mode** for registered health center admins to manage hospital and doctor records through a secure login.

---

## Table of Contents

- [Features](#features)
- [App Screen Flow](#app-screen-flow)
- [Technologies Used](#technologies-used)
- [Download](#download)
- [Usage](#usage)
- [Known Limitations](#known-limitations)
- [Guidelines](#guidelines)
- [License](#license)

---

## Features

### User Features
- Browse the list of hospitals in the Sylhet region, including name, email, location, and hotline number.
- Browse doctor profiles with name, chamber location, specialization, and contact number.
- Search hospitals in real time by **name or location** using the search bar.
- Search doctors in real time by **name, chamber location, or specialization**.

### HealthCenter (Admin) Features
- Register a new HealthCenter admin account using email and password (Firebase Authentication).
- Log in to a secure admin dashboard.
- **Add** new hospital records (name, email, location, hotline).
- **Update** existing hospital records by matching name and at least one other field.
- **Delete** hospital records by providing both name and location.
- **Add** new doctor records (name, chamber location, specialization, contact).
- **Update** existing doctor records by matching doctor name and at least one other field.
- **Delete** doctor records by providing the doctor's name.

### General Features
- Animated splash screen with background music on launch.
- Click sound feedback on button presses throughout the app.
- Real-time data sync powered by Firebase Realtime Database.
- Form validation with inline error messages on all input screens.
- Toast notifications for all success and failure outcomes.

---

## App Screen Flow

```
MainActivity (Splash + Animation)
└── MainActivity2 (Landing — choose User or HealthCenter)
    ├── [User Path] MainActivity3
    │   ├── MainActivity5 — Hospital list with search
    │   └── MainActivity6 — Doctor list with search
    │
    └── [HealthCenter Path] MainActivity4 (Register) / MainActivity7 (Login)
        └── MainActivity8 (Admin Dashboard)
            ├── MainActivity9 — Hospital management menu
            │   ├── MainActivity11 — Add hospital
            │   ├── MainActivity13 — Update hospital
            │   └── MainActivity15 — Delete hospital
            └── MainActivity10 — Doctor management menu
                ├── MainActivity12 — Add doctor
                ├── MainActivity14 — Update doctor
                └── MainActivity16 — Delete doctor
```

---

## Technologies Used

### Frontend
- **Java** (Android Development) — all activity logic is written in Java
- **Android SDK** — compileSdk 34, minSdk 24 (Android 7.0+), targetSdk 34
- **XML** — all UI layouts

### Backend / Database
- **Firebase Realtime Database** — hospital data stored under `NLH` node, doctor data under `SPH` node
- **Firebase Authentication** — email and password authentication for HealthCenter admin accounts
- **FirebaseUI Database** (firebase-ui-database 7.2.0) — used for RecyclerView adapter support

### Key Libraries (from build.gradle)
| Library | Version | Purpose |
|---|---|---|
| androidx.appcompat | 1.6.1 | AppCompatActivity base |
| com.google.android.material | 1.11.0 | Material UI components |
| androidx.constraintlayout | 2.1.4 | XML layout engine |
| firebase-auth | 22.3.1 | Admin login / registration |
| firebase-database | 20.3.1 | Realtime database CRUD |
| firebase-ui-database | 7.2.0 | FirebaseRecyclerAdapter support |
| com.karumi:dexter | 6.0.2 | Runtime permission handling |

---

## Download

Go to the link below to download the android apk and get started.

https://drive.google.com/file/d/1qUuNi5_Gxa-CtV0puSpH4qQZu_JOxhs6/view?usp=sharing

---

## Usage

### As a User

1. Launch the app — a splash screen with animation plays for ~4 seconds.
2. On the landing screen, tap **User**.
3. Choose to browse **Hospitals** or **Doctors**.
4. Use the **search bar** at the top to filter results:
   - Hospital search filters by name or location.
   - Doctor search filters by name, chamber location, or specialization.

### As a HealthCenter Admin

1. On the landing screen, tap **HealthCenter**.
2. **Register** a new account (email + password, minimum 6 characters) or **Log in** with an existing account.
3. After login, choose to manage **Hospitals** or **Doctors**.
4. From the management menu, choose **Add**, **Update**, or **Delete**.

**Adding a record:** Fill in all fields and tap submit. All fields are required.

**Updating a hospital:** Enter the hospital name and at least one other matching field (email, location, or hotline). The matching record will be updated.

**Deleting a hospital:** Enter both the hospital name and location exactly as stored.

**Updating a doctor:** Enter the doctor name and at least one other matching field (chamber, specialization, or contact).

**Deleting a doctor:** Enter the doctor's name exactly as stored.

---

## Known Limitations

The following features were planned but are **not yet implemented** in the current version:

- **Location-based nearby search:** The `location` field stores plain text (e.g., "Zindabazar"), not GPS coordinates. Distance-based sorting and GPS integration are not implemented.
- **Duplicate record prevention:** Adding a hospital or doctor with an identical name creates a new record rather than detecting the duplicate.
- **Admin session verification:** Admin screens do not verify the Firebase Auth session on entry. A logout button is also not yet implemented.
- **Empty state messages:** The list screens do not display a "No results found" message when a search returns zero results.
- **Offline / no-internet feedback:** The app does not display a message when there is no internet connection before attempting Firebase operations.
- **MainActivity17:** Registered in the Manifest but not yet connected to any navigation flow or feature.

---

## Guidelines

- Write clean and maintainable **Java** code (the project uses Java, not Kotlin, despite the original README).
- Follow the Android Activity lifecycle properly — release resources like `MediaPlayer` in `onDestroy()`.
- Test features on both an emulator and a real physical device.
- Do not expose API keys or Firebase credentials in public repositories. Rotate the API key in `google-services.json` if this repository is made public.
- Configure Firebase Realtime Database security rules before deploying to production so that only authenticated users can write data.

---

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
