// import React from 'react';
// import img2 from '../assets/Urology.png';
// import img3 from '../assets/Neurology.png';
// import img4 from '../assets/Gastroenterology.png';
// import img5 from '../assets/Oncology.png';
// import img6 from '../assets/joint.png';
// import img7 from '../assets/spine.png';
// import img8 from '../assets/Endocrinology.png';
// import img9 from '../assets/Cardology.png';

// const HorizontalCard = () => {
//   return (
//     <>
//       <div className="container-fluid py-5">
//         <div className="text-center mb-5">
//           <h1>Superspeciality Departments</h1>
//           <p className="mx-auto" style={{ maxWidth: '800px' }}>
//             We believe that a person can be best cured in a comfortable homely
//             atmosphere & we strive to create an ambience where the patient feels
//             more at home than at hospital
//           </p>
//         </div>

//         <div className="row">
//           {/* First Row */}
//           <div className="col-md-3 mb-4">
//             <div
//               className="card text-center h-100"
//               style={{
//                 borderColor: 'steelblue',
//                 borderRadius: '30px',
//                 cursor: 'pointer',
//               }}
//             >
//               <img
//                 src={img3}
//                 className="card-img-top mx-auto mt-3"
//                 alt="Neurology"
//                 style={{ width: '150px', height: '150px', objectFit: 'cover' }}
//               />
//               <div className="card-body d-flex flex-column">
//                 <h5 className="card-title">Neurology & Neurosurgery</h5>
//                 <div className="mt-auto">
//                   <button className="btn btn-primary btn-sm">more</button>
//                 </div>
//               </div>
//             </div>
//           </div>

//           <div className="col-md-3 mb-4">
//             <div
//               className="card text-center h-100"
//               style={{
//                 borderColor: 'steelblue',
//                 borderRadius: '30px',
//                 cursor: 'pointer',
//               }}
//             >
//               <img
//                 src={img2}
//                 className="card-img-top mx-auto mt-3"
//                 alt="Urology"
//                 style={{ width: '150px', height: '150px', objectFit: 'cover' }}
//               />
//               <div className="card-body d-flex flex-column">
//                 <h5 className="card-title">Urology & Nephrology</h5>
//                 <div className="mt-auto">
//                   <button className="btn btn-primary btn-sm">more</button>
//                 </div>
//               </div>
//             </div>
//           </div>

//           <div className="col-md-3 mb-4">
//             <div
//               className="card text-center h-100"
//               style={{
//                 borderColor: 'steelblue',
//                 borderRadius: '30px',
//                 cursor: 'pointer',
//               }}
//             >
//               <img
//                 src={img5}
//                 className="card-img-top mx-auto mt-3"
//                 alt="Oncology"
//                 style={{ width: '150px', height: '150px', objectFit: 'cover' }}
//               />
//               <div className="card-body d-flex flex-column">
//                 <h5 className="card-title">Oncology & Oncosurgery</h5>
//                 <div className="mt-auto">
//                   <button className="btn btn-primary btn-sm">more</button>
//                 </div>
//               </div>
//             </div>
//           </div>

//           <div className="col-md-3 mb-4">
//             <div
//               className="card text-center h-100"
//               style={{
//                 borderColor: 'steelblue',
//                 borderRadius: '30px',
//                 cursor: 'pointer',
//               }}
//             >
//               <img
//                 src={img4}
//                 className="card-img-top mx-auto mt-3"
//                 alt="Gastroenterology"
//                 style={{ width: '120px', height: '120px', objectFit: 'cover' }}
//               />
//               <div className="card-body d-flex flex-column">
//                 <h5 className="card-title">
//                   Gastroenterology and Gastrosurgery
//                 </h5>
//                 <div className="mt-auto">
//                   <button className="btn btn-primary btn-sm">more</button>
//                 </div>
//               </div>
//             </div>
//           </div>

//           {/* Second Row */}
//           <div className="col-md-3 mb-4">
//             <div
//               className="card text-center h-100"
//               style={{
//                 border: 'none',
//                 borderRadius: '30px',
//                 cursor: 'pointer',
//               }}
//             >
//               <img
//                 src={img6}
//                 className="card-img-top mx-auto mt-3"
//                 alt="Joint Replacement"
//                 style={{ width: '120px', height: '120px', objectFit: 'cover' }}
//               />
//               <div className="card-body d-flex flex-column">
//                 <h5 className="card-title">
//                   Joint Replacement, Arthroscopy & Sports Medicine
//                 </h5>
//                 <div className="mt-auto">
//                   <button className="btn btn-primary btn-sm">more</button>
//                 </div>
//               </div>
//             </div>
//           </div>

//           <div className="col-md-3 mb-4">
//             <div
//               className="card text-center h-100"
//               style={{
//                 border: 'none',
//                 borderRadius: '30px',
//                 cursor: 'pointer',
//               }}
//             >
//               <img
//                 src={img7}
//                 className="card-img-top mx-auto mt-3"
//                 alt="Spine Surgery"
//                 style={{
//                   width: '150px',
//                   height: '150px',
//                   objectFit: 'cover',
//                   borderRadius: '50%',
//                 }}
//               />
//               <div className="card-body d-flex flex-column">
//                 <h5 className="card-title">Spine Surgery</h5>
//                 <div className="mt-auto">
//                   <button className="btn btn-primary btn-sm">more</button>
//                 </div>
//               </div>
//             </div>
//           </div>

//           <div className="col-md-3 mb-4">
//             <div
//               className="card text-center h-100"
//               style={{
//                 border: 'none',
//                 borderRadius: '30px',
//                 cursor: 'pointer',
//               }}
//             >
//               <img
//                 src={img8}
//                 className="card-img-top mx-auto mt-3"
//                 alt="Endocrinology"
//                 style={{ width: '150px', height: '150px', objectFit: 'cover' }}
//               />
//               <div className="card-body d-flex flex-column">
//                 <h5 className="card-title">Endocrinology Department</h5>
//                 <div className="mt-auto">
//                   <button className="btn btn-primary btn-sm">more</button>
//                 </div>
//               </div>
//             </div>
//           </div>

//           <div className="col-md-3 mb-4">
//             <div
//               className="card text-center h-100"
//               style={{
//                 border: 'none',
//                 borderRadius: '30px',
//                 cursor: 'pointer',
//               }}
//             >
//               <img
//                 src={img9}
//                 className="card-img-top mx-auto mt-3"
//                 alt="Cardiology"
//                 style={{ width: '150px', height: '150px', objectFit: 'cover' }}
//               />
//               <div className="card-body d-flex flex-column">
//                 <h5 className="card-title">Cardiology Department</h5>
//                 <div className="mt-auto">
//                   <button className="btn btn-primary btn-sm">more</button>
//                 </div>
//               </div>
//             </div>
//           </div>
//         </div>
//       </div>
//     </>
//   );
// };

// export default HorizontalCard;
import React from 'react';
import './HorizontalCard.css';

const HorizontalCard = () => {
  const departments = [
    {
      icon: '🧠',
      title: 'Neurology & Neurosurgery',
    },
    {
      icon: '🫀',
      title: 'Urology & Nephrology',
    },
    {
      icon: '🏥',
      title: 'Oncology & Oncosurgery',
    },
    {
      icon: '🫁',
      title: 'Gastroenterology and Gastrosurgery',
    },
    {
      icon: '🦴',
      title: 'Joint Replacement, Arthroscopy & Sports Medicine',
    },
    {
      icon: '🦿',
      title: 'Spine Surgery',
    },
    {
      icon: '⚕️',
      title: 'Endocrinology Department',
    },
    {
      icon: '💗',
      title: 'Cardiology Department',
    },
  ];

  return (
    <div className="departments-section">
      <h2>Superspeciality Departments</h2>
      <p>
        We believe that a person can be best cured in a comfortable homely
        atmosphere & we strive to create an ambience where the patient feels
        more at home than at hospital
      </p>

      <div className="departments-grid">
        {departments.map((dept, index) => (
          <div key={index} className="department-card">
            <div className="dept-icon">{dept.icon}</div>
            <h3>{dept.title}</h3>
            <button className="more-btn">MORE</button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default HorizontalCard;
