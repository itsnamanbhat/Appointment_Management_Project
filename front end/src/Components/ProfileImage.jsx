import React from 'react';


const ProfileImage = ({ name }) => {
    const getInitials = (name) => {
        if (!name) return '';
        return name
            .split(' ')
            .map(part => part.charAt(0))
            .join('')
            .toUpperCase();
    };

    const initials = getInitials(name);

    return (
        <div className="profile-image">
            {initials}
        </div>
    );
};

export default ProfileImage;
