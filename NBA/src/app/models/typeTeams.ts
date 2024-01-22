export type SingleTeamTeams = {
    id: number;
    nbaFranchise: boolean;
    nickname: string;
    city: string;
    logo: string;
    allStar: boolean;
    conferenceName: string;
    teamName: string;
    divisionName: string;
};

export type division = {
    NorthWest: SingleTeamTeams[];
    SouthWest: SingleTeamTeams[];
    SoutHeast: SingleTeamTeams[];
    Atlantic: SingleTeamTeams[];
    Central: SingleTeamTeams[];
    Pacific: SingleTeamTeams[];
};
