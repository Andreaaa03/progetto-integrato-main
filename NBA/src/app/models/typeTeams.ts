export type SingleTeamTeams = {
    id: string;
    nbaFranchise: boolean;
    nickname: string;
    city: string;
    logo: string;
    allStar: boolean;
    conferenceName: string;
    teamName: string;
    divisionName: string;
    favourite: boolean;
};

export type division = {
    NorthWest: SingleTeamTeams[];
    SouthWest: SingleTeamTeams[];
    SoutHeast: SingleTeamTeams[];
    Atlantic: SingleTeamTeams[];
    Central: SingleTeamTeams[];
    Pacific: SingleTeamTeams[];
};
