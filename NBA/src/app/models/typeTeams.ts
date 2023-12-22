export type SingleTeamTeams = {
    id: number;
    name: string;
    nickname: string;
    code: string;
    city: string;
    logo: string;
    allStar: boolean;
    nbaFranchise: boolean;
    leagues: {
        standard: {
            conference: string;
            division: string | null;
        };
        vegas: {
            conference: string;
            division: string | null;
        };
        utah: {
            conference: string;
            division: string | null;
        };
        sacramento: {
            conference: string;
            division: string | null;
        };
    };
};

export type division = {
    NorthWest: SingleTeamTeams[];
    SouthWest: SingleTeamTeams[];
    SoutHeast: SingleTeamTeams[];
    Atlantic: SingleTeamTeams[];
    Central: SingleTeamTeams[];
    Pacific: SingleTeamTeams[];
};
